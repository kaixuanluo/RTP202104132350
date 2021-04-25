package com.example.rtp202104132350;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private String TAG = ExampleUnitTest.class.getSimpleName();

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testNalu() {
        System.out.println("testNalu:" + Arrays.toString(new Nalu().getNalu()));
    }
}