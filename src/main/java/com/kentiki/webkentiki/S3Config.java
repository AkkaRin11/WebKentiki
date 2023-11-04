package com.kentiki.webkentiki;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class S3Config {

    @Bean
    public AmazonS3 s3client() {
        // это нужно исправить
        AWSCredentials credentials = new BasicAWSCredentials(
                "YCAJEQBd-u_F9_gLZtbkcAXNW",
                "YCP2I9xVm45sz0rUzRJGzFBDa8iN4_kzXveSprX3"
        );

        return AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withEndpointConfiguration(
                        new AmazonS3ClientBuilder.EndpointConfiguration(
                                "storage.yandexcloud.net","ru-central1"
                        )
                )
                .build();
    }




}
