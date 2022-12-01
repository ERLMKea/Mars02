package com.example.mars02.service;

import com.example.mars02.model.Foto;
import com.example.mars02.model.Photo;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServicePhotosImpl implements ApiServicePhotos {

    private RestTemplate restTemplate;
    public ApiServicePhotosImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String rogerUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY";
    String preserverenceUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/perseverance/photos?sol=100&page=2&api_key=DEMO_KEY";

    String preserverenceLatestUrl = "https://api.nasa.gov/mars-photos/api/v1/rovers/perseverance/latest_photos?api_key=";
    String apiKey = "cXrM4POFLGgBFlOOAbiEk3K0q2NfzynY0Ckydqap";

    @Override
    public List<Photo> getPhotos() {
        ResponseEntity<Foto> photoResponse =
                restTemplate.exchange(rogerUrl,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Foto>() {
                        });
        Foto foto = photoResponse.getBody();
        List<Photo> photos =  foto.getPhotos();
        return photos;

    }

    @Override
    public List<Photo> getPerseverancePhotos() {
        String url = preserverenceUrl;
        ResponseEntity<Foto> photoResponse =
                restTemplate.exchange(url,
                        HttpMethod.GET, null, new ParameterizedTypeReference<Foto>() {
                        });
        Foto foto = photoResponse.getBody();
        List<Photo> photos =  foto.getPhotos();
        return photos;

    }


}
