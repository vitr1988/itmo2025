package ru.itmo.javaadvanced.lesson2;

import ru.itmo.javaadvanced.lesson2.service.AccountService;
import ru.itmo.javaadvanced.lesson2.service.impl.FakeAccountService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

public class AccountRunner {

    public static void main(String[] args) {
        String accountNumber = "1234567890";
        AccountService accountService = new FakeAccountService();
        accountService.deposit(accountNumber, new BigDecimal(1000));
        accountService.withdraw(accountNumber, new BigDecimal(500));
        accountService.deposit(accountNumber, new BigDecimal(200));
        System.out.println(accountService.getBalance(accountNumber));

        AccountService service = (AccountService) Proxy.newProxyInstance(AccountRunner.class.getClassLoader(),
                new Class[]{AccountService.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                return null;
            }
        });
        service.withdraw(accountNumber, new BigDecimal(500));
        System.out.println(accountService.getBalance(accountNumber));
    }
}
