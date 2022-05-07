package com.test.project;

import java.io.File;

import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

public class S3Helper {
    public String putObjectToS3(Region region, String bucket, String fileName, File file) {
        System.out.println(bucket);
        S3Client client = S3Client.builder().region(region).build();
        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket).key(fileName).build();
        PutObjectResponse response = client.putObject(request,
                RequestBody.fromFile(file));
        client.close();

        return response.toString();
    }
}
