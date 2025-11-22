package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.java.MathOperation;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class MathOperationTest {

    @Test
    void testAdd() {
        MathOperation obj = new MathOperation();
        assertEquals(5, obj.add(2, 3));
    }

    @Test
    void testMultiply() {
        MathOperation obj = new MathOperation();
        assertEquals(6, obj.multiply(2, 3));
    }

    @Test
    void testValidateName() {
        MathOperation obj = new MathOperation();
        assertTrue(obj.validateName("ames"));
    }

    @Test
    void testNotNullName() {
        String name = "John";
        assertNotNull(name);
    }

    @Test
    void testValidationFlag() {
        boolean status = true;
        assertTrue(status);
    }
}

