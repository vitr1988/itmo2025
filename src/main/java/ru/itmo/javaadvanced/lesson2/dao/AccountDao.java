package ru.itmo.javaadvanced.lesson2.dao;

import java.math.BigDecimal;

public interface AccountDao {
    void deposit(String accountNumber, BigDecimal amount);
    void withdraw(String accountNumber, BigDecimal amount);
}
