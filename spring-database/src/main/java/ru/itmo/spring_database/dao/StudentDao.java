package ru.itmo.spring_database.dao;

import ru.itmo.spring_database.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

    Long create(Long id, String firstName,  String lastName, String patronymic);
    void assignToGroup(Long id, Long groupId);

    void deleteById(Long id);
    List<Student> findAll();
    Optional<Student> findById(Long id);
}
