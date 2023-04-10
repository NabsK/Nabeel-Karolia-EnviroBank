package com.envirobank.controller;

import com.envirobank.dao.AccountProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/image")        //localhost:8080/v1/api/image
public class ImageController {

    @Autowired
    private AccountProfileRepository repo;  //dao

    @GetMapping("/{name}/{surname}")
    public FileSystemResource getHttpImageLink(@PathVariable String name, @PathVariable String surname) {
        return null;
    }

}
