package com.example.mars02.mockito;

import com.example.mars02.model.Photo;
import com.example.mars02.service.PhotoService;
import com.example.mars02.service.PhotoService2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class InlineMockTest01 {


    @Autowired
    PhotoService photoService;

    PhotoService2 photoService2;


    @Test
    void testInlineMock() {
        Map<Integer, String> mapreal = new HashMap<>();
        mapreal.put(123, "photo123");
        String str = mapreal.get(123);
        System.out.println(str);
        assertEquals("photo123", str);
    }

    @Test
    void testInlineMock2() {
        Map mapMock = mock(Map.class);
        mapMock.put(123, "photo123");
        Object obj = mapMock.get(123);
        System.out.println(obj);
        assertEquals("photo123", ""+obj);
    }

    @Test
    void testInlineMock3() {
        Map mapMock = mock(Map.class);
        when(mapMock.get(123)).thenReturn("photo123");
        assertEquals(0, mapMock.size());
        assertEquals("photo123", mapMock.get(123));
    }

    @Test
    void testPhotoService() {
        PhotoService mockserv = mock(PhotoService.class);
        List<Photo> photos = mockserv.findAllLastDigit(4);
        assertEquals(0, photos.size());
    }

    @Test
    void testPhotoService2() {
        PhotoService2 mockserv = mock(PhotoService2.class);
        List<Photo> photos = mockserv.findAllPhotoLastDigit(4);
        ArrayList<Photo> lstPhoto = new ArrayList<>();
        Photo ph1 = new Photo();
        ph1.setId(1234);
        lstPhoto.add(ph1);
        when(mockserv.findAllPhotoLastDigit(4)).thenReturn(lstPhoto);

        assertEquals(1, mockserv.findAllPhotoLastDigit(4).size());
    }



}
