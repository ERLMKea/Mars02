package com.example.mars02.service;

import com.example.mars02.model.Photo;
import com.example.mars02.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> findAllLastDigit(Integer digit) {
        List<Photo> photos = photoRepository.findAll();

        List<Photo> photosModulus = photos.stream().filter(photo -> {
            Integer i = photo.getId();
            Integer j =  i - (i / 10) * 10;
            return j == digit;
        }).collect(Collectors.toList());

        return photosModulus;
    }

}
