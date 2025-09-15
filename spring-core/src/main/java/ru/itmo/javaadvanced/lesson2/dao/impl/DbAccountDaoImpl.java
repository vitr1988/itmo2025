package ru.itmo.javaadvanced.lesson2.dao.impl;

import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.dao.AccountDao;

import java.math.BigDecimal;

@Component("dbAccountDao")
public class DbAccountDaoImpl implements AccountDao {

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {

    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {

    }
}
