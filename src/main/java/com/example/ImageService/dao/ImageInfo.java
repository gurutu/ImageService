package com.example.ImageService.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "imageInfo")
public class ImageInfo {
    
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String image_name;
  private String hmac;

  public ImageInfo(String image_name,String hmac){
      this.image_name=image_name;
      this.hmac=hmac;
  }




}
