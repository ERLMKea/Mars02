package com.example.mars02.controller;

import com.example.mars02.model.Camera;
import com.example.mars02.model.Photo;
import com.example.mars02.repository.CameraRepository;
import com.example.mars02.repository.PhotoRepository;
import com.example.mars02.repository.RoverRepository;
import com.example.mars02.service.ApiServicePhotos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class FotoRESTController {

    @Autowired
    ApiServicePhotos apiServicePhotos;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    CameraRepository cameraRepository;

    @Autowired
    RoverRepository roverRepository;

    @GetMapping("/photosx")
    List<Photo> getPhotos() {
        return apiServicePhotos.getPhotos();
    }


    @GetMapping("/photosfromcamera26")
    List<Photo> getPhotosCamera26() {
        List<Photo> lstPhoto = photoRepository.findAll();
        lstPhoto = lstPhoto.stream().filter(f -> f.getCamera().getCameraId() == 26).collect(Collectors.toList());
        return lstPhoto;
    }

    @PutMapping("/{id}")
    public Photo updatePhoto(
            @PathVariable Integer id, @RequestBody Photo photo) {
        return photoRepository.save(photo);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePhoto(@PathVariable Integer id) {
        try {
            Optional<Photo> phopt = photoRepository.findById(id);
            if (phopt.isPresent()) {
                var obj = phopt.get();
              photoRepository.delete(phopt.get());
            }
            return new ResponseEntity<>("Photo with id: id deleted" + id, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/savephotos")
    List<Photo> savePhotos() {
        List<Photo> lstPhotos = apiServicePhotos.getPhotos();
        for (Photo ph: lstPhotos) {
            cameraRepository.save(ph.getCamera());
            roverRepository.save(ph.getRover());
            photoRepository.save(ph);
        }
        lstPhotos = apiServicePhotos.getPhotos();
        return lstPhotos;

    }

    @GetMapping("/savedescr/{descr}")
    List<Photo> saveDescription(@PathVariable String descr) {
        List<Photo> lstPhotos = photoRepository.findAll();
        for (Photo ph: lstPhotos) {
            cameraRepository.save(ph.getCamera());
            roverRepository.save(ph.getRover());
            ph.setDescription((descr.equals("null") ? null : descr));
            photoRepository.save(ph);
        }
        lstPhotos = apiServicePhotos.getPhotos();
        return lstPhotos;
    }


    @GetMapping("/saveper")
    List<Photo> savePerPhotos() {
        List<Photo> lstPhotos = apiServicePhotos.getPerseverancePhotos();
        for (Photo ph: lstPhotos) {
            cameraRepository.save(ph.getCamera());
            roverRepository.save(ph.getRover());
            photoRepository.save(ph);
        }
        lstPhotos = apiServicePhotos.getPhotos();
        return lstPhotos;

    }
    @GetMapping("/cameras")
    List<Camera> getCameras() {
        return cameraRepository.findAll();
    }

    @GetMapping("/getdesc")
    List<Photo> getDesc() {
        List<Photo> lstPhotos = photoRepository.findAll();
        List<Photo>  lstDescr =  lstPhotos.stream().filter(f -> Objects.nonNull(f.getDescription()))
                .filter(f -> f.getDescription().length()>0)
                .collect(Collectors.toList());


        return lstDescr;
    }

    @PutMapping("/photo/{id}")
    public ResponseEntity<Photo> updatePhoto(@PathVariable String id, @RequestBody Photo photo) {
        Optional<Photo> orgPhoto = photoRepository.findById(photo.getId());
        if (orgPhoto.isPresent()) {
            photoRepository.save(photo);
            return new ResponseEntity<>(orgPhoto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new Photo(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/photo")
    public ResponseEntity<Photo> updatePhoto(@RequestBody Photo photo) {
        photoRepository.save(photo);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }


    @GetMapping("/photosfromnavcamera")
    List<Photo> getPhotosFromNavCamera() {
        List<Photo> lstPhotos = photoRepository.findAll();
        lstPhotos = lstPhotos.stream().filter(f -> Objects.nonNull(f.getCamera()))
                .filter(f -> f.getCamera().getCameraId() > 20)
                .collect(Collectors.toList());
        return lstPhotos;
    }

}
