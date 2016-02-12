package com.guatec;

import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

import static org.testng.Assert.*;

/**
 * Created by juanliska on 1/11/16.
 */
public class ExampleTest {

    @Test
    public void testIsOk() throws Exception {
        Example example = new Example();
        assert (example.isOk());
    }
    @Test
    public void testLoadResource() {
        ClassLoader classLoader = getClass().getClassLoader();
        final URL resource = classLoader.getResource("com/guatec/test-data.txt");
        assert (resource != null);
        File f = new File(resource.getFile());
        assert(f.exists());
    }
}