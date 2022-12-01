package com.example.mars02.repository;

import com.example.mars02.model.Photo;
import net.bytebuddy.TypeCache;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    List<Photo> findPhotoByDescriptionIsLike(String description);

    List<Photo> findPhotoByDescriptionIsLikeOrImgSrcLike(String description, String imgSrc);

    List<Photo> findAll(Sort sort);

    List<Photo> findPhotoByCameraCameraId(Integer id);

    Integer countAllById(Integer Id);

    List<Photo> findPhotoByIdBetween(Integer id1, Integer id2);


}

