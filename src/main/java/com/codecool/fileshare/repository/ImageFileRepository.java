package com.codecool.fileshare.repository;

import org.springframework.stereotype.Component;

@Component("file")
public class ImageFileRepository implements ImageRepository{

    public String storeImage(String category, String content){
        // Optional task: implement store in file here.
        // different categories should be go to different folder.
        // if you want this method to run instead of jdbc version find @Qualifier("jdbc") in
        // service and change to @Qualifier("file")
        return null;
    }

    public String readImage(String uuid){
        // Optional task: implement read from file here.
        // if you want this method to run instead of jdbc version find @Qualifier("jdbc") in
        // service and change to @Qualifier("file")
        return null;
    }
}
