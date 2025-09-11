package ru.itmo.javaadvanced.lesson3.component;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Getter
@Setter
//@Component
@ConfigurationProperties(prefix = "itmo")
public class AppResources {

    private Integer numValue;
    private String strValue;
    private Resource resValue;
    private boolean flag;

}
