package com.test.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import java.util.Map;

/**
 * Hello world!
 *
 */
public class LambdaHandler implements RequestHandler<Map<String, String>, String> {
    static final String BUCKET = "deploy-lambda-codingmentor";
    static final String FILE_NAME = "java-folder/java.txt";
    static final Region REGION = Region.EU_WEST_3;

    @Override
    public String handleRequest(Map<String, String> input, Context context) {

        try {
            S3Client client = S3Client.builder().region(REGION).build();
            PutObjectRequest request = PutObjectRequest.builder()
                    .bucket(BUCKET).key(FILE_NAME).build();
            PutObjectResponse response = client.putObject(request,
                    RequestBody.fromString("content"));
            client.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return "Done";
    }

}
