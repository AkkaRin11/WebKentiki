package com.kentiki.webkentiki.service;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;

import com.amazonaws.services.codecommit.model.File;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import com.google.storage.v2.ListObjectsResponse;
import com.google.storage.v2.Object;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.FileOutputStream;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 s3client;
    private final String bucketName = "webkentiki";

    public void downloadNewImg() {

        //s3client.putObject(bucketName, "test.jpg", "static/images/1.jpg");
//        S3Object s3object = s3client.getObject(bucketName, "3.jpg");
//        S3ObjectInputStream inputStream = s3object.getObjectContent();
//
//        try {
//            inputStream.transferTo(new FileOutputStream(
//                    "C:/important files/Projects/WebKentiki/src/main/resources/static/images/t3.jpg"));
//            inputStream.close();
//        } catch (Exception e){
//            System.out.println(e);
//        }

        try {
            List<S3ObjectSummary> list = s3client.listObjects(bucketName).getObjectSummaries();

            for (int i = 0; i < list.size(); i++){
                S3Object s3object = s3client.getObject(bucketName, list.get(i).getKey());
                S3ObjectInputStream inputStream = s3object.getObjectContent();

                inputStream.transferTo(new FileOutputStream(
                        "C:/important files/Projects/WebKentiki/src/main/resources/static/img/" + list.get(i).getKey()));
                System.out.println("dowload to C:/important files/Projects/WebKentiki/src/main/resources/static/img/" + list.get(i).getKey());

                inputStream.close();
            }


            for (int i = 0; i < list.size(); i++){
                s3client.deleteObject(bucketName, list.get(i).getKey());
            }
        } catch (Exception e){
            System.out.println(e);
        }

    }



}
