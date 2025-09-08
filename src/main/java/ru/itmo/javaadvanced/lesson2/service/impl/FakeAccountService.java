package ru.itmo.javaadvanced.lesson2.service.impl;

import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.service.AccountService;

import java.math.BigDecimal;

//@Primary
@Component("fakeService")
public class FakeAccountService implements AccountService {

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {

    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {

    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return null;
    }
}
