package ru.itmo.spring_database.dao.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.itmo.spring_database.dao.GroupDao;
import ru.itmo.spring_database.model.Group;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final NamedParameterJdbcOperations jdbcTemplate;

    private static final RowMapper<Group> GROUP_ROW_MAPPER =
            (rs, rowNum) -> new Group(rs.getLong("id"), rs.getString("name"));

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
                        """,
                GROUP_ROW_MAPPER
        );
    }

    @Override
    public Optional<Group> findById(Long id) {
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject("""
                            select id, name from groups
                            where id = :id
                            """,
                    Map.of("id", id),
                    GROUP_ROW_MAPPER)
            );
        } catch (EmptyResultDataAccessException e) {
            log.error("Exception happens", e);
            return Optional.empty();
        }
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        jdbcTemplate.update("delete from groups where id in (:ids)", Map.of("ids", ids));
    }
}
