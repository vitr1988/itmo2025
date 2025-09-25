package ru.itmo.jpa.config;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Data
@Validated
@ConfigurationProperties("data")
public class AppProperties {

    @NotNull
    private String criticalValue;
}
