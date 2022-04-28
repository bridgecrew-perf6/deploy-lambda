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
            String response = new S3Helper().putObjectToS3(REGION, BUCKET, FILE_NAME);
            System.out.println(response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        return "Done";
    }

}
