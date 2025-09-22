package ru.itmo.jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ru.itmo.jpa.model.Group;
import ru.itmo.jpa.model.Student;
import ru.itmo.jpa.repository.GroupRepository;
import ru.itmo.jpa.repository.StudentRepository;
import ru.itmo.jpa.util.OffsetLimitPageable;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@SpringBootApplication
public class SpringDatabaseApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringDatabaseApplication.class, args);
		GroupRepository groupRepository = applicationContext.getBean(GroupRepository.class);
		List<Long> ids = IntStream.range(50, 100)
				.boxed()
				.map(it -> it * 1000L)
				.toList();
		groupRepository.deleteAllById(ids);

		for (Long id : ids) {
			groupRepository.save(new Group("A" + id));
		}

		groupRepository.updateNameById(5L, "B5000");

//		groupRepository.findById(5L).ifPresent(
//				group -> {
//					group.setName("B5000");
//					groupRepository.save(group);
//				}
//		);

		groupRepository.deleteById(1000L);

//		Page<Group> first20GroupsPage = groupRepository.findAll(PageRequest.of(0, 20, Sort.Direction.DESC));
		Page<Group> first20GroupsPage = groupRepository.findAll(OffsetLimitPageable.of(0, 20, Sort.Direction.DESC, "id"));
		System.out.println("First 20 groups: " + first20GroupsPage.getContent());

		System.out.println(groupRepository.findById(-1000L));

		List<Long> studentIds = LongStream.range(4, 8)
				.boxed()
				.toList();
		StudentRepository studentRepository = applicationContext.getBean(StudentRepository.class);
		studentRepository.deleteAllById(studentIds);
		for (Long id : studentIds) {
			studentRepository.save(new Student(id, "Ivan_" + id, "Ivanov", "Ivanovich"));
		}
		studentRepository.findById(4L).ifPresent(student -> {
			studentRepository.assignToGroup(
					student.getId(),
					4L //groupRepository.findById(100L).map(Group::getId).orElse(null)
			);
		});

//		studentRepository.findById(4L).ifPresent(student -> {
//			student.setGroup(groupRepository.findById(4L).orElse(null));
//			studentRepository.save(student);
//		});

		studentRepository.deleteById(5L);

		System.out.println(studentRepository.findAll());
		System.out.println(studentRepository.findById(4L).orElse(null));
	}

}
