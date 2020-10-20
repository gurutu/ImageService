package com.example.ImageService.utils;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

@Component
public class ImageUtil {
    @Autowired
    ResourceLoader resourceLoader;

    private File getfilePath() {
        Resource rs = resourceLoader.getResource("classpath:static" + "/image/blur.jpg");
        try {
            return rs.getFile();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new File("");
        }
}


    public File getFileFromURL() {
        File file = null;
        try {
            
           System.out.println(" Ker s"+getfilePath().getParentFile()); 
            file = new File(""+getfilePath().getParentFile());
            File[] listOfFiles = file.listFiles();
            int min=0;
            int max=9;
            Random random=new Random();
           int va= random.nextInt(max - min + 1) + min;
            return listOfFiles[va];
        } catch (Exception e) {
            e.printStackTrace();
              return file;
        } 
    }

    public int randomNumber(){
        int min=0;
        int max=9;
        Random random=new Random();
        return  random.nextInt(max - min + 1) + min;
    }
  public String[] imagename(){
    String ls[]={
        "blur.jpg",
        "grayscale.jpg",
        "image1.jpg",
        "image2.jpg",
        "image3.jpg",
        "image4.jpg",
        "image5.jpg",
        "image6.jpg",
        "image7.jpg",
        "laptop.jpg"
       };
       return ls;
  }
    public String randomName(){
        
        return imagename()[randomNumber()];
    }

    public File getFileUsingFileName(String filename) {
        File file = null;
        try {
            URI url = resourceLoader.getResource("classpath:static" + "/image/"+filename).getURI();
            file = new File(url);
            File[] listOfFiles = file.listFiles();
            int min=0;
            int max=9;
            Random random=new Random();
           int va= random.nextInt(max - min + 1) + min;
            return listOfFiles[va];
        } catch (Exception e) {
            e.printStackTrace();
              return file;
        } 
    }


    public String getHostName(String str){
        String split[]=str.split("/");
        return split[0]+"//"+split[2];
    }
}
