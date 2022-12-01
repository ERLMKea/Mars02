package com.example.mars02.service;

import com.example.mars02.model.Camera;
import com.example.mars02.model.Photo;

import java.util.List;

public interface PhotoService2 {

    List<Photo> findAllPhotoLastDigit(Integer digit);
    List<Camera> findAllCameraLastDigit(Integer digit);



}
