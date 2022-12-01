package com.example.mars02.controller;

import com.example.mars02.exception.ResourceNotFoundException;
import com.example.mars02.model.Photo;
import com.example.mars02.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "exp/")
public class ExceptController {

    @Autowired
    PhotoRepository photoRepository;

    @GetMapping("/")
    public String hello() {
        return "hej";
    }

    @GetMapping("/filex/{filename}")
    public String getFilename(@PathVariable String filename) {
        return filename;
    }

    @GetMapping("/file/{filename}")
    public String getFile(@PathVariable String filename) {
        String fileContent = "x";
        try {
            FileInputStream fs = new FileInputStream("c:/AFile/" + filename);
            try {
                byte[] bytes = fs.readAllBytes();
                fileContent = new String(bytes);
                System.out.println(fileContent);
            } catch (IOException ioerr) {
                System.out.println("IOExp ioerr=" + ioerr);
            }
        } catch (FileNotFoundException e) {
            System.out.println("FILE NOT Found e=" + e.getMessage());
        }
        return fileContent;
    }

    @GetMapping("/divx/{divnum}")
    public int getdivnumx(@PathVariable int divnum) {
        int i1 = 100;
        int i2 = 88;
        try {
            i2 = 100 / divnum;
        } catch (ArithmeticException ae) {
            System.out.println("ae=" + ae);
        }
        return i2;
    }

    @GetMapping("/div/{divnum}")
    public int getdivnum(@PathVariable int divnum) {
        int i1 = 100;
        int i2 = 100 / divnum;
        return i2;
    }

    @GetMapping("/loop/{loopnum}")
    public int getloop(@PathVariable int loopnum) {
        int x = 0;
        for (int i = 0; i<loopnum; i++) {
            x++;
        }
        return x;
    }

    @GetMapping("/photox/{id}")
    public ResponseEntity<Photo> findPhotoByIdx(@PathVariable int id) {
        Optional<Photo> obj = photoRepository.findById(id);
        if (obj.isPresent()) {
            return new ResponseEntity<>(obj.get(), HttpStatus.OK);
        } else {
            Photo ph = new Photo();
            ph.setDescription("Not Found");
            return new ResponseEntity<>(ph, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/photo/{id}")
    public ResponseEntity<Photo> findPhotoById(@PathVariable int id) {
        Photo ph = photoRepository.findById(id).orElseThrow(
                ()-> new ResourceNotFoundException("We couldnt find photo with id = " + id)
        );
        return new ResponseEntity<>(ph, HttpStatus.OK);
    }


}



