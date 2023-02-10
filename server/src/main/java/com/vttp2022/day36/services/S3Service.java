package com.vttp2022.day36.services;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;

@Service
public class S3Service {

    @Autowired
    private AmazonS3 s3Client;

    public String upload(MultipartFile file) throws IOException {

        // user data
        Map<String, String> userData = new HashMap<>();
        userData.put("name", "fred");
        userData.put("uploadTime", (new Date()).toString());
        userData.put("originalFilename", file.getOriginalFilename());

        // metadata of the file
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());
        metadata.setUserMetadata(userData);

        // create a put request
        String key = UUID.randomUUID().toString().substring(0, 8);
        PutObjectRequest putReq = new PutObjectRequest(
                "jhtan",
                "myobjects/%s".formatted(key),
                file.getInputStream(),
                metadata);

        // allow public access
        putReq.withCannedAcl(CannedAccessControlList.PublicRead);

        s3Client.putObject(putReq);

        return key;

    }
}
