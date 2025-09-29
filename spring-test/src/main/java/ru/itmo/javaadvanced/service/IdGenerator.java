package ru.itmo.javaadvanced.service;

import lombok.Data;

import java.util.concurrent.ThreadLocalRandom;

@Data
public class IdGenerator implements Generator {

    @Override
    public Long generateId() {
        return ThreadLocalRandom.current().nextLong(0, 100);
    }
}
