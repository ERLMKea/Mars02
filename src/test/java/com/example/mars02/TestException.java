package com.example.mars02;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestException {

    @Test
    void testArithmExcpetion() {
        assertThrows(ArithmeticException.class, () -> {
            int result = 3 / 0;
        });

    }


}
