package ru.itmo.javaadvanced.lesson2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan(basePackages = {"ru.itmo.javaadvanced.lesson1", "ru.itmo"})
public class ApplicationConfiguration {

    @Bean
    String fullName() {
        return "Иванов Виталий Андреевич";
    }
}
