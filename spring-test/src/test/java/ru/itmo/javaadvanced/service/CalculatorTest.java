package ru.itmo.javaadvanced.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class CalculatorTest {

    private static Calculator underTest;

    @BeforeAll
    public static void init() {
        Generator mock = Mockito.mock(Generator.class);
        Mockito.when(mock.generateId()).thenReturn(1L);
        underTest = new Calculator(mock);
    }

    @BeforeEach
    public void clear() {
        underTest.clearCounter();
    }

    @Test
    public void shouldCalculatorCorrectlyWorks() {
        Assertions.assertEquals(5, underTest.summa(2, 3));
        Assertions.assertEquals(1, underTest.summa(-2, 3));
        Assertions.assertEquals(10, underTest.summa(-2, 12));

        Assertions.assertEquals(3, underTest.getCounter());
    }

    @Test
    public void shouldCalculatorIncorrectlyWorks() {
        Assertions.assertNotEquals(-5, underTest.summa(2, 3));
        Assertions.assertNotEquals(-1, underTest.summa(-2, 3));
        Assertions.assertNotEquals(-10, underTest.summa(-2, 12));

        Assertions.assertEquals(3, underTest.getCounter());
    }
}
