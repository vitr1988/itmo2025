package ru.itmo.jpa.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GroupDto {
    @Min(1)
    @Max(9007199254740991L)
    private Long id;

    @Size(min = 1, max = 255)
    private String name;

    @Size(max = 255)
    private String nameEn;
}
