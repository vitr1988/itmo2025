package ru.itmo.javaadvanced.lesson2.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.dao.AccountDao;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class AccountDaoImpl implements AccountDao {

    private final String ivanov;

    public static final Map<String, BigDecimal> ACCOUNTS = new HashMap<>();

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        BigDecimal value = ACCOUNTS.computeIfAbsent(accountNumber, key -> new BigDecimal(0)).add(amount);
        ACCOUNTS.put(accountNumber, value);
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        deposit(accountNumber, amount.multiply(new BigDecimal(-1)));
    }
}
