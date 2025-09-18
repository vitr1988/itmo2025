package ru.itmo.spring_database.dao.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.spring_database.dao.StudentDao;
import ru.itmo.spring_database.model.Group;
import ru.itmo.spring_database.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

    private static final RowMapper<Student> STUDENT_ROW_MAPPER =
            (rs, rowNum) -> {
                Long groupId = rs.getLong("group_id");
                if (rs.wasNull()) {
                    groupId = null;
                }
                return new Student(
                        rs.getLong("id")
                        , rs.getString("first_name")
                        , rs.getString("last_name")
                        , rs.getString("patronymic")
                        , groupId == null
                                ? null
                                : new Group(rs.getLong("group_id"), rs.getString("group_name"))
                );
            };

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Long create(Long id, String firstName, String lastName, String patronymic) {
        jdbcTemplate.update("""
                insert into students(id, first_name, last_name, patronymic)
                values (:id, :firstName, :lastName, :patronymic)
                """,
                Map.of("id", id,
                        "firstName", firstName,
                        "patronymic", patronymic,
                        "lastName", lastName));
        return id;
    }

    @Override
    public void assignToGroup(Long id, Long groupId) {
        jdbcTemplate.update("""
                update students set group_id = :groupId
                where id = :id
                """, Map.of("groupId", groupId,  "id", id));
    }

    @Override
    public void deleteById(Long id) {
        jdbcTemplate.update("""
                delete from students
                where id = :id
                """, Map.of("id", id));
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("""
                select s.id, s.first_name, s.last_name, s.patronymic,
                       g.id group_id, g.name group_name 
                from students s
                left join groups g on g.id = s.group_id
                """, STUDENT_ROW_MAPPER
        );
    }

    @Override
    public Optional<Student> findById(Long id) {
        return jdbcTemplate.query("""
                select s.id, s.first_name, s.last_name, s.patronymic,
                       g.id group_id, g.name group_name
                from students s
                left join groups g on g.id = s.group_id
                where s.id = :id
                """,
                Map.of("id", id),
                STUDENT_ROW_MAPPER
        ).stream().findFirst();
    }

    @Override
    @Transactional
    public void deleteAllByIds(List<Long> ids) {
        jdbcTemplate.update("""
                delete from students
                where id in (:ids)
                """
                , Map.of("ids", ids)
        );
    }
}
