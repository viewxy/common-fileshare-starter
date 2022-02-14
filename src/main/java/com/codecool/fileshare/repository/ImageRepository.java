package com.codecool.fileshare.repository;

public interface ImageRepository {
    public String storeImage(String category, String content);
    public String readImage(String uuid);
}
