package com.example.ImageService.repository;

import com.example.ImageService.dao.ImageInfo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CrudRepository<ImageInfo, Long> {

	ImageInfo findByHmac(String hmac);

    
}
