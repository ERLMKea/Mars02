package com.example.mars02;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class TestArray {

    @Test
    void testArr() {
        int[] numbers = {12, 3, 4, 1};
        int[] expected = {1, 3, 4, 12};
        Arrays.sort(numbers);
        //assertEquals(expected, numbers);
        assertArrayEquals(expected, numbers);
    }



    void testArr2() {
        //int[] numbers = {1,12,3,4};
        int[] numbers = null;
        int[] expected = {1, 3, 4, 12};
        try {
            Arrays.sort(numbers);
            assertArrayEquals(expected, numbers);
        } catch (NullPointerException nullerr) {
            System.out.println("Nullexp err=" + nullerr.getMessage());
            fail("failed in sort nullerr=" + nullerr);
        }


    }
}