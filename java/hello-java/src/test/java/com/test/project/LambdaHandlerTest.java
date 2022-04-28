package com.test.project;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class LambdaHandlerTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LambdaHandlerTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LambdaHandlerTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testHandler()
    {
        assertTrue( true );
    }
}
