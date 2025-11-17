package calculator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calc = new Calculator();

    @Test
    void testAdd() {
        assertEquals(5.0, calc.add(2, 3));
        assertEquals(-1.0, calc.add(-2, 1));
        assertEquals(0.0, calc.add(0, 0));
    }

    @Test
    void testSubtract() {
        assertEquals(1.0, calc.subtract(3, 2));
        assertEquals(-3.0, calc.subtract(-2, 1));
    }

    @Test
    void testMultiply() {
        assertEquals(6.0, calc.multiply(2, 3));
        assertEquals(0.0, calc.multiply(0, 5));
        assertEquals(-4.0, calc.multiply(-2, 2));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, calc.divide(6, 3));
        assertEquals(-2.0, calc.divide(-4, 2));
    }

    @Test
    void testDivideByZero() {
        assertThrows(IllegalArgumentException.class, () -> calc.divide(5, 0));
    }
}
