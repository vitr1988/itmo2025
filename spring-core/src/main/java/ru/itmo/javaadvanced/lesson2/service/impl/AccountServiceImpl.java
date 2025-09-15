package ru.itmo.javaadvanced.lesson2.service.impl;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.itmo.javaadvanced.lesson2.dao.AccountDao;
import ru.itmo.javaadvanced.lesson2.service.AccountService;
import ru.itmo.javaadvanced.lesson2.service.PrintService;

import java.math.BigDecimal;
import java.util.Map;

import static ru.itmo.javaadvanced.lesson2.dao.impl.AccountDaoImpl.ACCOUNTS;
import static ru.itmo.javaadvanced.lesson2.service.impl.AccountServiceImpl.ACCOUNT_SERVICE_IMPL;

@Service(ACCOUNT_SERVICE_IMPL)
@Primary
//@Scope(SCOPE_PROTOTYPE) // каждое обращение к бину будет инициировать создание нового объекта
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountServiceImpl implements AccountService {

    public static final String ACCOUNT_SERVICE_IMPL = "accountServiceImpl";

    //    @Setter(onMethod_ = @Autowired)
    private final AccountDao accountDao;
    private final ApplicationContext applicationContext;
//    private final PrintService printService;

    public AccountServiceImpl(//@Qualifier("dbAccountDao")
                              Map<String, AccountDao> accountDao, ApplicationContext applicationContext) {
        this.accountDao = accountDao.values().stream().findFirst().get();
        this.applicationContext = applicationContext;
//        this.printService = printService;
    }

    @PostConstruct
    public void init() {
        System.out.println("Данный бин %s готов к использованию".formatted(this.getClass().getSimpleName()));
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Данный бин %s удален из контекста".formatted(this.getClass().getSimpleName()));
    }

//    private static final AccountService INSTANCE = new AccountServiceImpl();

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {
        accountDao.deposit(accountNumber, amount);
//        printService.print(getBalance(accountNumber) + "");
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {
        accountDao.withdraw(accountNumber, amount);
//        printService.print(getBalance(accountNumber) + "");
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {
        return ACCOUNTS.get(accountNumber);
    }

    @Override
    public int getId() {
//        return printService.getId();
        return applicationContext.getBean(PrintService.class).getId();
    }

    //    public static AccountService getInstance() {
//        return INSTANCE;
//    }

//    @Autowired
//    public void setAccountDao(AccountDao accountDao) {
//        this.accountDao = accountDao;
//    }
}
