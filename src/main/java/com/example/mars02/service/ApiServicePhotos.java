package com.example.mars02.service;

import com.example.mars02.model.Photo;

import java.util.List;

public interface ApiServicePhotos {
    List<Photo> getPhotos();
    List<Photo> getPhotos(int sol);
}
