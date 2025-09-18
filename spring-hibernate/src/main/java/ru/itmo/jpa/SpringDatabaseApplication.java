package ru.itmo.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.jpa.dao.GroupDao;
import ru.itmo.jpa.dao.StudentDao;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootApplication
public class SpringDatabaseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringDatabaseApplication.class, args);
//		GroupDao groupDao = applicationContext.getBean(GroupDao.class);
//		List<Long> ids = IntStream.range(50, 100)
//				.boxed()
//				.map(it -> it * 1000L)
//				.toList();
//		groupDao.deleteAllByIds(ids);
//		for (Long id : ids) {
//			groupDao.create("A" + id);
//		}
//
//		groupDao.updateNameById(5L, "B5000");
//
//		groupDao.deleteById(1000L);
//
//		System.out.println(groupDao.findAll());
//		System.out.println(groupDao.findById(-1000L));
//
//		List<Long> studentIds = LongStream.range(4, 8)
//				.boxed()
//				.toList();
		StudentDao studentDao = applicationContext.getBean(StudentDao.class);
//		studentDao.deleteAllByIds(studentIds);
//		for (Long id : studentIds) {
//			studentDao.create(id, "Ivan_" + id, "Ivanov", "Ivanovich");
//		}
//		studentDao.findById(4L).ifPresent(student -> {
//			studentDao.assignToGroup(
//					student.getId(),
//					200L //groupDao.findById(100L).map(Group::getId).orElse(null)
//			);
//		});
//
//		studentDao.deleteById(5L);
//
//		System.out.println(studentDao.findAll());
		System.out.println(studentDao.findById(4L).orElse(null));
	}

}
