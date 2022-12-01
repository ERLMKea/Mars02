package com.example.mars02.controller;

import com.example.mars02.model.Camera;
import com.example.mars02.model.Photo;
import com.example.mars02.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class JPQLRestController {

    @Autowired
    PhotoRepository photoRepository;

    @GetMapping("/photosdesc/{descr}")
    List<Photo> getPhotosDescriptions(@PathVariable String descr) {
        return photoRepository.findPhotoByDescriptionIsLike("%"+descr+"%");
    }

    @GetMapping("/photos")
    List<Photo> getPhotosDescrip(@RequestParam String descr) {
        return photoRepository.findPhotoByDescriptionIsLike(descr);
    }

    @GetMapping("/photosmanyx")
    Map<String, String> getPhotosManyx(@RequestParam Map<String,String> requestParams) {
        Map<String, String > maps = new HashMap<>();
        maps.put("1", "2");
        return maps;
    }

    @GetMapping("/photosmany")
    List<Photo> getPhotosMany(@RequestParam Map<String,String> requestParams) {
        String descr = requestParams.get("descr");
        String imgsrc = requestParams.get("imgsrc");
        return photoRepository.findPhotoByDescriptionIsLikeOrImgSrcLike(descr, "%"+imgsrc+"%");
    }

    @GetMapping("/sortby")
    List<Photo> sortPhotos(@RequestParam Map<String,String> requestParams) {
        String descr = requestParams.get("descr");
        String imgsrc = requestParams.get("imgsrc");
        return photoRepository.findAll(Sort.by(descr).descending().and(Sort.by(imgsrc).ascending()));
        //return photoRepository.findAll(Sort.by(descr).descending());  virker
    }


    @GetMapping("/cameraid/{id}")
    List<Photo> getPhotosDescriptions(@PathVariable Integer id) {
        return photoRepository.findPhotoByCameraCameraId(id);
    }

    @GetMapping("/photosids/{id1}/{id2}")
    List<Photo> getPhotosBetweenId(@PathVariable Integer id1, @PathVariable Integer id2) {
        Photo photo = new Photo();
        photo.setId(id1);
        Camera camera = new Camera();
        camera.setCameraId(id2);
        photo.setCamera(camera);

        //Photo photo2 = new Photo();
        //photo2.setDescription("%nice%");
        return photoRepository.findAll(Example.of(photo));
    }

}
