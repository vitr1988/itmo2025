package ru.itmo.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id
    private Long id;

    private String firstName;
    private String lastName;
    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(Long id, String firstName, String lastName, String patronymic) {
        this(id, firstName, lastName, patronymic, null);
    }
}
