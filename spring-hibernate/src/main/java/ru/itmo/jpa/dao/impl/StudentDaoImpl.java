package ru.itmo.jpa.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.jpa.dao.StudentDao;
import ru.itmo.jpa.model.Group;
import ru.itmo.jpa.model.Student;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class StudentDaoImpl implements StudentDao {

    private final EntityManager em;

    @Override
    @Transactional
    public Long create(Long id, String firstName, String lastName, String patronymic) {
        Student student = new Student(id, firstName, lastName, patronymic);
        em.persist(student);
        return id;
    }

    @Override
    @Transactional
    public void assignToGroup(Long id, Long groupId) {
        Student student = id == null ? null : em.find(Student.class, id);
        Group group = groupId == null ? null : em.find(Group.class, groupId);
        if (student != null) {
            student.setGroup(group);
            em.merge(student);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        em.remove(em.find(Student.class, id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Student> findAll() {
        return em.createQuery("select s from Student s", Student.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findById(Long id) {
        return em.createQuery("""
            select s from Student s
            join fetch s.group
            where s.id = :id
        """).setParameter("id", id)
            .getResultList().stream().findFirst();
//        return Optional.ofNullable(em.find(Student.class, id));
    }

    @Override
    @Transactional
    public void deleteAllByIds(List<Long> ids) {
        em.createNativeQuery("delete from students where id in (:ids)")
                .setParameter("ids", ids)
                .executeUpdate();
    }
}
