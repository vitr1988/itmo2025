package ru.itmo.jpa.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itmo.jpa.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
//
//    @Query("""
//    SELECT u FROM User u
//        join fetch u.roles r
//            where u.login = :login
//    """)
    @EntityGraph(value = "User.role")
    Optional<User> findByLogin(String login);
}
