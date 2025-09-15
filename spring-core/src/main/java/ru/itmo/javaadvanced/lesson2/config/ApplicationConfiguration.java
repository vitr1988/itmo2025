package ru.itmo.javaadvanced.lesson2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.itmo.javaadvanced.lesson2.service.AccountService;
import ru.itmo.javaadvanced.lesson2.service.PrintService;

@Configuration
@RequiredArgsConstructor
//@ComponentScan(basePackages = {"ru.itmo.javaadvanced.lesson1", "ru.itmo"})
public class ApplicationConfiguration {

    @Bean
    String fullName(PrintService printService, AccountService accountService) {
        return "Иванов Виталий Андреевич";
    }
}
