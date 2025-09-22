package ru.itmo.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.jpa.model.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Transactional
    @Modifying
    @Query("""
        update Group g set g.name = :name
        where g.id = :id
    """)
    void updateNameById(long id, String name);
}
