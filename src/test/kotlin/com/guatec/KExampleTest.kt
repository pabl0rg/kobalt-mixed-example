package com.guatec

import org.testng.annotations.Test

import org.testng.Assert.*

/**
 * Created by juanliska on 1/11/16.
 */
class KExampleTest {

    @Test
    fun testGetOk() {
        val ke = KExample()
        assertTrue(ke.ok)
    }
}