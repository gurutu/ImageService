package com.example.ImageService.controller;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.example.ImageService.dao.ImageInfo;
import com.example.ImageService.repository.ImageRepository;
import com.example.ImageService.utils.ImageUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class ImageController {

    @Autowired 
    ImageUtil imageUtil;
    @Autowired
    ImageRepository imageRepo;
    @Autowired
    ResourceLoader resourceLoader;

	

    @GetMapping("/random")
    public RedirectView redirectWithUsingRedirectView(RedirectAttributes attributes) {
        attributes.addFlashAttribute("flashAttribute", "redirectWithRedirectView");
        attributes.addAttribute("hmac", UUID.randomUUID());
        return new RedirectView("img");
    }

   @RequestMapping(value = "/img", method = RequestMethod.GET, produces = "image/jpg")
	public byte[] getImageFile(@RequestParam(required = false) String hmac)  {
        byte[] bytes = null;
		try { 
            ImageInfo findByHmac = imageRepo.findByHmac(hmac);
            if(findByHmac==null){
               String fileName=imageUtil.randomName();
                imageRepo.save(new ImageInfo(fileName, hmac));
                InputStream inputStream = resourceLoader.getResource("classpath:static/image/" + fileName).getInputStream();
                bytes =FileCopyUtils.copyToByteArray(inputStream);
            }else{
                InputStream inputStream = resourceLoader.getResource("classpath:static/image/" + findByHmac.getImage_name()).getInputStream();
                bytes =FileCopyUtils.copyToByteArray(inputStream);
            }
           
		} catch (Exception e) {
            e.printStackTrace();
		}
		return bytes;
    }
  
    @GetMapping("/images")
    public List<String> listOfImage(HttpServletRequest request) {
        List<String> list=new ArrayList<>();
        String st=request.getRequestURL().toString();
        String hostname = imageUtil.getHostName(st);
        for(String image:imageUtil.imagename()){
            list.add(hostname+"/api/image/"+image);
        }
       
        return list;
    }
    @GetMapping(value = "/image/{name}",produces = "image/jpg")
    public byte[] getImageByName(@PathVariable("name") String name) {
        byte[] bytes = null;
		try { 
                InputStream inputStream = resourceLoader.getResource("classpath:static/image/" + name).getInputStream();
                bytes =FileCopyUtils.copyToByteArray(inputStream);
		} catch (Exception e) {
            e.printStackTrace();
		}
		return bytes;
    }
    
      

    
}
