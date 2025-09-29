package ru.itmo.javaadvanced.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

@DisplayName("Генератор идентификатор должен ")
public class IdGeneratorTest {

    private IdGenerator underTest = new IdGenerator();

    @DisplayName("корректно формировать новое значение, не допуская null-значений")
//    @Order(1)
    @Test
    public void shouldCorrectlyGenerateIdWithoutNull() {
        Assertions.assertNotNull(underTest.generateId());
    }

    @DisplayName("корректно формировать новое значение в диапозоне от 0 до 100")
//    @Order(2)
    @Test
    public void shouldCorrectlyGenerateIdInRange() {
        Assertions.assertTrue(underTest.generateId() < 100);
        Assertions.assertTrue(underTest.generateId() >= 0);
    }
}
