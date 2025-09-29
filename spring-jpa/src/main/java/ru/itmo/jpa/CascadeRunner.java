//package ru.itmo.jpa;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import ru.itmo.jpa.model.Group;
//import ru.itmo.jpa.model.Student;
//import ru.itmo.jpa.repository.GroupRepository;
//import ru.itmo.jpa.repository.StudentRepository;
//
//@SpringBootApplication
//public class CascadeRunner {
//
//    public static void main(String[] args) {
//        ConfigurableApplicationContext applicationContext = SpringApplication.run(CascadeRunner.class, args);
//        GroupRepository groupRepository = applicationContext.getBean(GroupRepository.class);
//        Group group = groupRepository.save(new Group("TEST!!!±"));
//
//        StudentRepository studentRepository = applicationContext.getBean(StudentRepository.class);
//        studentRepository.save(new Student(100000L, "Test", "Test", "Test", group));
//    }
//}
