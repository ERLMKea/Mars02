package com.example.mars02.service;

import com.example.mars02.model.Photo;
import com.example.mars02.model.Foto;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServicePhotosImpl implements ApiServicePhotos{

    String photoUrlSol10 = "https://api.nasa.gov/mars-photos/api/v1/rovers/perseverance/photos?sol=10&api_key=DEMO_KEY";
    //api_key=cXrM4POFLGgBFlOOAbiEk3K0q2NfzynY0Ckydqap

    private RestTemplate restTemplate;
    public ApiServicePhotosImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Photo> getPhotos() {
        System.out.printf("GetPhotos from Nasa");
        ResponseEntity<Foto> photoResponse =
                restTemplate.exchange(photoUrlSol10,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Foto>() {
                        });
        Foto foto = photoResponse.getBody();
        List<Photo> photos =  foto.getPhotos();
        return photos;
    }

    String getUrlFromSol(int sol) {
        String url1 = "https://api.nasa.gov/mars-photos/api/v1/rovers/perseverance/photos?sol=";
        String url2 = url1 + sol + "&api_key=cXrM4POFLGgBFlOOAbiEk3K0q2NfzynY0Ckydqap";
        return url2;
    }

    @Override
    public List<Photo> getPhotos(int sol) {
        System.out.printf("GetPhotos from Nasa");
        String urlsol = getUrlFromSol(sol);
        ResponseEntity<Foto> photoResponse =
                restTemplate.exchange(urlsol,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Foto>() {
                        });
        Foto foto = photoResponse.getBody();
        List<Photo> photos =  foto.getPhotos();
        return photos;
    }
}
