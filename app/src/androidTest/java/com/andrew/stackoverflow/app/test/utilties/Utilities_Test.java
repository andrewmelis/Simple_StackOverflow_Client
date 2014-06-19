package com.andrew.stackoverflow.app.test.utilties;

import com.andrew.stackoverflow.app.utilities.Utilities;

import junit.framework.TestCase;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Utilities_Test extends TestCase {

    public void testConvertInputStreamToString() {
        String expectedString = "{\"fake\":\"JSON\"}";

        byte[] data = expectedString.getBytes();
        InputStream is = new ByteArrayInputStream(data);

        String actualString = Utilities.convertInputStreamToString(is);
        assertEquals(expectedString, actualString);
    }

}