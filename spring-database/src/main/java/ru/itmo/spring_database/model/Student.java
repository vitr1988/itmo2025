package ru.itmo.spring_database.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private Long groupId; // Group group;
}
