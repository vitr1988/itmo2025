package ru.itmo.javaadvanced.lesson3.component;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TextResource {

    @Value("${itmo.num_value}")
    private Integer numValue;

    @Value("${itmo.str_value}")
    private String strValue;

    @Value("${itmo.res_value}")
    private Resource resValue;

    @Value("${itmo.flag}")
    private boolean boolValue;
}
