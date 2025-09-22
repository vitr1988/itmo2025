package ru.itmo.jpa.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.itmo.jpa.model.Student;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Transactional
    @Modifying
    @Query("""
        update Student student set student.group.id = :groupId
        where student.id = :studentId
    """)
    void assignToGroup(@Param("studentId") Long id, Long groupId);

    @Query("""
        select student
            from Student student 
            join student.group
        where student.firstName like concat(:firstName, '%')
        order by student.id      
    """)
    List<Student> findByFirstNameLike(String firstName, Pageable pageable);
}
