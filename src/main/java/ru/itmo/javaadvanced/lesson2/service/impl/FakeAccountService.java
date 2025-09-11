package ru.itmo.javaadvanced.lesson2.service.impl;

import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.service.AccountService;

import java.math.BigDecimal;

import static ru.itmo.javaadvanced.lesson2.service.impl.FakeAccountService.FAKE_SERVICE;

//@Primary
@Component(FAKE_SERVICE)
public class FakeAccountService implements AccountService {

    public static final String FAKE_SERVICE = "fakeService";

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {

    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {

    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return null;
    }
}
