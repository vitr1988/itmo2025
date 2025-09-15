package ru.itmo.javaadvanced.lesson3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.itmo.javaadvanced.lesson3.component.AppResources;
import ru.itmo.javaadvanced.lesson3.component.TextResource;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class SpringBootRunner {

    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext = SpringApplication.run(SpringBootRunner.class, args);
        TextResource textResource = applicationContext.getBean(TextResource.class);
        System.out.printf("Num value: %s\n", textResource.getNumValue());
        System.out.printf("Str value: %s\n", textResource.getStrValue());
        System.out.printf("Res value: %s\n", textResource.getResValue().getContentAsString(StandardCharsets.UTF_8));
        System.out.printf("Bool value: %s\n", textResource.isBoolValue());

        AppResources appResources = applicationContext.getBean(AppResources.class);
        System.out.printf("Number value: %s\n", appResources.getNumValue());
        System.out.printf("String value: %s\n", appResources.getStrValue());
        System.out.printf("Resource value: %s\n", appResources.getResValue().getContentAsString(StandardCharsets.UTF_8));
        System.out.printf("Boolean value: %s\n", appResources.isFlag());
        Thread.sleep(Long.MAX_VALUE);
    }
}
