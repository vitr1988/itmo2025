package ru.itmo.spring_database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.itmo.spring_database.dao.GroupDao;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootApplication
public class SpringDatabaseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringDatabaseApplication.class, args);
		GroupDao groupDao = applicationContext.getBean(GroupDao.class);
		List<Long> ids = IntStream.range(50, 100)
				.boxed()
				.map(it -> it * 1000L)
				.toList();
		groupDao.deleteAllByIds(ids);
		for (Long id : ids) {
			groupDao.create(id, "A" + id);
		}

		groupDao.updateNameById(5000L, "B5000");

		groupDao.deleteById(1000L);

		System.out.println(groupDao.findAll());
	}

}
