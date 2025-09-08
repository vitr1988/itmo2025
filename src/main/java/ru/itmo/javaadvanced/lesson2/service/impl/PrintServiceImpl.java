package ru.itmo.javaadvanced.lesson2.service.impl;

import ch.qos.logback.core.testUtil.RandomUtil;
import lombok.Getter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.service.PrintService;

import java.util.Random;

@Component
@Scope("prototype")
public class PrintServiceImpl implements PrintService {

    @Getter
    private int id = new Random().nextInt();

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
