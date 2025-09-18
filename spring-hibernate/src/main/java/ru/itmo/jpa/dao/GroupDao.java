package ru.itmo.jpa.dao;

import ru.itmo.jpa.model.Group;

import java.util.List;
import java.util.Optional;

public interface GroupDao {

    Long create(String name);
    Long create(Long id, String name);
    void updateNameById(Long id, String name);
    void deleteById(Long id);

    void save(Group entity);

    List<Group> findAll();
    Optional<Group> findById(Long id);

    void deleteAllByIds(List<Long> ids);
}
