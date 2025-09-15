package ru.itmo.spring_database.dao;

import ru.itmo.spring_database.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDao {

    Long create(Long id, String name);
    void updateNameById(Long id, String name);
    void deleteById(Long id);

    List<Group> findAll();
    Optional<Group> findById(Long id);

    void deleteAllByIds(List<Long> ids);
}
