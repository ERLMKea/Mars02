package com.example.mars02;

import com.example.mars02.model.Camera;
import com.example.mars02.model.Photo;
import com.example.mars02.repository.CameraRepository;
import com.example.mars02.repository.PhotoRepository;
import com.example.mars02.service.PhotoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class Mars02ApplicationTests {

    @Autowired
    CameraRepository cameraRepository;

    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    PhotoService photoService;

    @Test
    void testSomeCameras() {
        List<Camera> cameras = cameraRepository.findAll();
        assertEquals(true, cameras.size() > 0);
    }

    @Test
    void testAtLeast5Cameras() {
        List<Camera> cameras = cameraRepository.findAll();
        assertEquals(true, cameras.size() > 5);
    }

    @Test
    void testAtLeast() {
        List<Camera> cameras = cameraRepository.findAll();
        assertEquals(true, cameras.size() > 5);
    }

    @Test
    void testFilterCamera() {
        List<Photo> photos = photoRepository.findAll();
        List<Photo> photosFilter = photos.stream().filter(photo -> photo.getCamera().getCameraId() == 20).collect(Collectors.toList());
        assertEquals(true, photosFilter.size()<photos.size());
    }

    @Test
    void testModulus() {
        Integer i = 20;
        Integer j = 20 % 7;
        assertEquals(6 , j);
    }

    @Test
    void testLastDigit4() {
        Integer i = 103384;
        Integer j = (i / 10) * 10;
        Integer q = i - j;
        System.out.println("div 10=" + j + " og q=" + q);
    }

    @Test
    void testLastDigit() {
        List<Photo> photos = photoService.findAllLastDigit(4);
        assertNotEquals(0, photos.size());
        assertEquals(true, photos.size()<100);
        Photo photo = photos.get(0);
        String str = "" + photo.getId();
        String digit = str.substring(str.length() - 1);
        assertEquals("4", digit);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5}) // six numbers
    void testLastDigitParam(int number) {
        List<Photo> photos = photoService.findAllLastDigit(number);
        assertNotEquals(0, photos.size());
        assertEquals(true, photos.size()<100);
        Photo photo = photos.get(0);
        String str = "" + photo.getId();
        String digit = str.substring(str.length() - 1);
        assertEquals(""+number, digit);
    }

    @Test
    void testFilterPhotoModulus() {
        List<Photo> photos = photoService.findAllLastDigit(4);
        System.out.println("photofilter4 =" + photos.size());

    }

}