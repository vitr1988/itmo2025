package ru.itmo.javaadvanced.lesson2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.javaadvanced.lesson2.service.AccountService;

import java.math.BigDecimal;
import java.text.NumberFormat;

import static ru.itmo.javaadvanced.lesson2.service.impl.AccountServiceImpl.ACCOUNT_SERVICE_IMPL;

@SpringBootApplication
public class SpringApplicationRunner {


    public static void main(String[] args) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        String accountNumber = "1234567890";
        try (ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringApplicationRunner.class, args)) {
            AccountService accountService = applicationContext.getBean(ACCOUNT_SERVICE_IMPL, AccountService.class);
//            AccountService accountService = applicationContext.getBean(FAKE_SERVICE, AccountService.class);
            AccountService accountService2 = applicationContext.getBean(ACCOUNT_SERVICE_IMPL, AccountService.class);
            AccountService accountService3 = applicationContext.getBean(ACCOUNT_SERVICE_IMPL, AccountService.class);
            accountService.deposit(accountNumber, new BigDecimal(1000));
            accountService.withdraw(accountNumber, new BigDecimal(500));
            accountService.deposit(accountNumber, new BigDecimal(200));
            System.out.println("Текущий баланс счета с номером %s %s".formatted(accountNumber, numberFormat.format(accountService.getBalance(accountNumber).longValue())));

            System.out.println("Идентичны ли объекты? " + accountService.equals(accountService2));

            System.out.println("Print service id: %d".formatted(accountService.getId()));
            System.out.println("Print service id: %d".formatted(accountService2.getId()));
            System.out.println("Print service id: %d".formatted(accountService3.getId()));
        }
    }
}
