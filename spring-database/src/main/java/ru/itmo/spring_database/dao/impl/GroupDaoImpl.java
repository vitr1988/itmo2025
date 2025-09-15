package ru.itmo.spring_database.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itmo.spring_database.dao.GroupDao;
import ru.itmo.spring_database.model.Group;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcOperations jdbcTemplate;

    @Override
    public Long create(Long id, String name) {
        jdbcTemplate.update("""
            insert into groups (id, name) values (:id, :name)
        """, Map.of("id", id, "name", name));
        return id;
    }

    @Override
    public void updateNameById(Long id, String name) {
        jdbcTemplate.update("""
            update groups set name = :name
            where id = :id
        """, Map.of("id", id, "name", name));
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("""
            delete from groups
            where id = :id
        """, Map.of("id", id));
    }

    @Override
    public List<Group> findAll() {
        return jdbcTemplate.query("""
                select id, name from groups
                order by id
                """, (rs, rowNum) -> new Group(rs.getLong("id"), rs.getString("name"))
        );
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.ofNullable(jdbcTemplate.queryForObject("""
                        select id, name from groups
                        where id = :id
                        """,
                Map.of("id", id),
                (rs, rowNum) -> new Group(rs.getLong("id"), rs.getString("name"))

        ));
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        jdbcTemplate.update("delete from groups where id in (:ids)", Map.of("ids", ids));
    }
}
