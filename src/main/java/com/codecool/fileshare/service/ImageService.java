package com.codecool.fileshare.service;

import com.codecool.fileshare.dto.ImageDTO;
import com.codecool.fileshare.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    @Qualifier("jdbc")
    ImageRepository imageRepository;

    public String storeImage(ImageDTO imageDTO, String category){
        return imageRepository.storeImage(category, imageDTO.getContent());
    }

    public ImageDTO getImage(String fileName){
        return  new ImageDTO(imageRepository.readImage(fileName));
    }
}
