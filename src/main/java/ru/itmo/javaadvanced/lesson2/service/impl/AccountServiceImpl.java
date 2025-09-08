package ru.itmo.javaadvanced.lesson2.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import ru.itmo.javaadvanced.lesson2.dao.AccountDao;
import ru.itmo.javaadvanced.lesson2.service.AccountService;
import ru.itmo.javaadvanced.lesson2.service.PrintService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static ru.itmo.javaadvanced.lesson2.dao.impl.AccountDaoImpl.ACCOUNTS;

@Component
@Primary
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {

    //    @Setter(onMethod_ = @Autowired)
    private final AccountDao accountDao;
    private final PrintService printService;

    public AccountServiceImpl(//@Qualifier("dbAccountDao")
                              Map<String, AccountDao> accountDao, PrintService printService) {
        this.accountDao = accountDao.values().stream().findFirst().get();
        this.printService = printService;
    }

//    private static final AccountService INSTANCE = new AccountServiceImpl();

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        accountDao.deposit(accountNumber, amount);
        printService.print(getBalance(accountNumber) + "");
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        accountDao.withdraw(accountNumber, amount);
        printService.print(getBalance(accountNumber) + "");
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return ACCOUNTS.get(accountNumber);
    }

//    public static AccountService getInstance() {
//        return INSTANCE;
//    }

//    @Autowired
//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }
}
