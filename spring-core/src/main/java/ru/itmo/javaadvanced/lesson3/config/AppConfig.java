package ru.itmo.javaadvanced.lesson3.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.itmo.javaadvanced.lesson3.component.AppResources;

@Configuration
@EnableConfigurationProperties(AppResources.class)
public class AppConfig {
}
