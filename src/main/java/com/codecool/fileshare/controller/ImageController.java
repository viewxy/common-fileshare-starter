package com.codecool.fileshare.controller;

import com.codecool.fileshare.dto.ImageDTO;
import com.codecool.fileshare.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/images")
public class ImageController {

    @Autowired
    ImageService imageService;

    @PostMapping("/{category}")
    public String storeImage(@RequestBody ImageDTO imageDTO, @PathVariable("category") String category){
        return imageService.storeImage(imageDTO,category);
    }

    @GetMapping("/{uuid}")
    public ImageDTO getImage(@PathVariable("uuid") String uuid){
        return imageService.getImage(uuid);
    }
}
