package com.test.project;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.mockito.Mockito;
import software.amazon.awssdk.regions.Region;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Unit test for simple App.
 */
public class LambdaHandlerTest{

    @Test
    public void testHandler() {
        S3Helper s3Helper = new S3Helper();
        S3Helper spiedS3Helper = spy(s3Helper);
        Mockito.doThrow(new Error("error")).when(spiedS3Helper).putObjectToS3(any(Region.class), anyString(), anyString());

        Map<String, String> input = new HashMap<String, String>();
        LambdaHandler handler = new LambdaHandler();

        String res = handler.handleRequest(input, null);

        System.out.println(res);
        assertTrue(res == "Done");
    }

}
