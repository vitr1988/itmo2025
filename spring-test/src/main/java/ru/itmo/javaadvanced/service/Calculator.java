package ru.itmo.javaadvanced.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Calculator {

    private final Generator idGenerator;

    @Getter
    private int counter;

    public int summa(int argA, int argB) {
        this.counter++;
        Long coeff = idGenerator.generateId();
        System.out.println(coeff);
        return argA + argB * Math.toIntExact(coeff);
    }

    public void clearCounter() {
        this.counter = 0;
    }
}
