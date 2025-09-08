package ru.itmo.javaadvanced.lesson2.service.impl;

import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.service.PrintService;

@Component
public class PrintServiceImpl implements PrintService {

//    private final AccountService accountService;
//
//    public PrintServiceImpl(@Lazy AccountService accountService) {
//        this.accountService = accountService;
//    }

    @Override
    public void print(String text) {
        System.out.println(text);
    }
}
