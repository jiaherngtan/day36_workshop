package com.vttp2022.day36.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.vttp2022.day36.services.S3Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;

@Controller
@RequestMapping
public class UploadController {

    @Autowired
    private S3Service s3Service;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postUpload(
            @RequestPart MultipartFile myfile,
            @RequestPart String name,
            Model model) {

        String key = "";

        try {
            key = s3Service.upload(myfile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        model.addAttribute("name", name);
        model.addAttribute("file", myfile);
        model.addAttribute("key", "myobjects/%s".formatted(key));

        return "upload";
    }

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin()
    public ResponseEntity<String> postUploadFromAngular(
            @RequestPart MultipartFile myImage,
            @RequestPart String title,
            @RequestPart String complain) {

        System.out.printf("title: %s\n", title);
        System.out.printf("complain: %s\n", complain);
        System.out.printf("file name: %s\n", myImage.getOriginalFilename());
        System.out.printf("content type: %s\n", myImage.getContentType());

        String key = "";

        try {
            key = s3Service.upload(myImage);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JsonObject payload = Json.createObjectBuilder()
                .add("imageKey", key)
                .build();

        return ResponseEntity.ok(payload.toString());
    }

    @PostMapping(path = "/sync", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin()
    public String syncContacts(@RequestBody String contacts) {
        System.out.println(">>> contacts: " + contacts);
        return "sync";
    }

    // day 39
    @PostMapping(path = "/post", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @CrossOrigin()
    public ResponseEntity<String> postFromAngular(
            @RequestPart MultipartFile myfile,
            @RequestPart String title,
            @RequestPart String text) {

        System.out.printf("title: %s\n", title);
        System.out.printf("text: %s\n", text);
        System.out.printf("file name: %s\n", myfile.getOriginalFilename());
        System.out.printf("content type: %s\n", myfile.getContentType());

        String key = "";

        try {
            key = s3Service.upload(myfile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        JsonObject payload = Json.createObjectBuilder()
                .add("imageUrl", key)
                .build();

        return ResponseEntity.ok(payload.toString());
    }

}
