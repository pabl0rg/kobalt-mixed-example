package com.guatec;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * Created by juanliska on 1/11/16.
 */
public class ExampleTest {

    @Test
    public void testIsOk() throws Exception {
        Example example = new Example();
        assert(example.isOk());
    }
}