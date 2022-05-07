package com.test.project;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import software.amazon.awssdk.regions.Region;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class LambdaHandler implements RequestHandler<Map<String, String>, String> {
    static final String BUCKET = "deploy-lambda-codingmentor";
    static final String FILE_NAME = "java-folder/java.txt";
    static final Region REGION = Region.EU_WEST_3;

    public static File writeToFile() {

        File file = new File("/tmp/TestFileABCDE.txt");
        try {
            FileWriter output = new FileWriter(file.getName());
            output.write("writing something to file");
            System.out.println("Data is written to the file.");
            output.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
        return file;
    }

    private static File createSampleFile(String fileName, String fileContent) throws IOException {
        File file = File.createTempFile(fileName, ".txt");
        file.deleteOnExit();

        Writer writer = new OutputStreamWriter(new FileOutputStream(file));
        writer.write(fileContent);
        writer.close();

        return file;
    }

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        try {
            File file = createSampleFile("testFile.txt", "Content Here");
            System.out.println(file.getAbsolutePath());
            String response = new S3Helper().putObjectToS3(REGION, BUCKET, "folder/saturday.txt", file);
            System.out.println(response);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }

        return "Done";
    }

}
