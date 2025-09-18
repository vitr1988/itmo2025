package ru.itmo.jpa.dao.impl;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.jpa.dao.GroupDao;
import ru.itmo.jpa.model.Group;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GroupDaoImpl implements GroupDao {

    private final EntityManager em;

    @Override
    @Transactional
    public Long create(String name) {
        Group entity = new Group(name);
        em.persist(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public Long create(Long id, String name) {
        Group entity = new Group(id, name);
        em.persist(entity);
        return entity.getId();
    }

    @Override
    @Transactional
    public void updateNameById(Long id, String name) {
        Group entity = em.find(Group.class, id);
        if (entity != null) {
            entity.setName(name);
            em.merge(entity);
        }
    }

    @Override
    @Transactional
    public void save(Group entity) {
        if (entity.getId() == null) {
            em.persist(entity);
        } else {
            em.merge(entity);
        }
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Group entity = em.find(Group.class, id);
        if (entity != null) {
            em.remove(entity);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Group> findAll() {
        return em.createQuery("select g from Group g", Group.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Group> findById(Long id) {
        return Optional.ofNullable(em.find(Group.class, id));
    }

    @Override
    @Transactional
    public void deleteAllByIds(List<Long> ids) {
        for (Long id : ids) {
            deleteById(id);
        }
    }
}
