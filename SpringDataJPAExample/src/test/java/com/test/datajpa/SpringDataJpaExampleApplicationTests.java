package com.test.datajpa;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.test.datajpa.entitites.Student;
import com.test.datajpa.repository.StudentRepository;

@SpringBootTest
//@DataJpaTest
class SpringDataJpaExampleApplicationTests {

	@Autowired
	private StudentRepository repo;

	private Student student;

	@BeforeEach
	public void setup() {
		student = Student.builder().id(491L).name("pradeep").score(590).build();
	}

	@DisplayName("Junit Test for Save Student Operation")
	@Test
	void testSaveStudent() {
		student = Student.builder().id(491L).name("pradeep").score(590).build();
		//student = Student.builder().name("pradeep").score(590).id(0L).build();
		repo.save(student);

		assertThat(student).isNotNull();
		assertThat(student.getId()).isGreaterThan(0);
	}

	@DisplayName("Junit Test for SaveAll Student Operation")
	@Test
	void testSaveAllStudent() {
		Student student1 = Student.builder().id(492L).name("punarvika").score(598).build();
		List<Student> list  = List.of(student,student1);

		repo.saveAll(list);
		List<Student> studentList = repo.findAll();

		// then - verify the output
		assertThat(studentList).isNotNull();
		assertThat(studentList.size()).isEqualTo(2);
	}

	@DisplayName("JUnit test for get student by id operation")
	@Test
	public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
		repo.save(student);

		// when - action or the behaviour that we are going test
		Student student1 = repo.findById(student.getId()).get();

		// then - verify the output
		assertThat(student1).isNotNull();
	}

	@DisplayName("JUnit test for update student operation")
	@Test
	public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {

		repo.save(student);

		// when - action or the behaviour that we are going test
		Student savedStudent = repo.findById(student.getId()).get();
		savedStudent.setName("K.V Jasvin");
		savedStudent.setScore(578);
		Student updatedStudent = repo.save(savedStudent);

		// then - verify the output
		assertThat(updatedStudent.getName()).isEqualTo("K.V Jasvin");
		assertThat(updatedStudent.getScore()).isEqualTo(578);
	}

	@DisplayName("JUnit test for delete Student operation")
	@Test
	public void givenEmployeeObject_whenDelete_thenRemoveEmployee() {

		repo.save(student);

		// when - action or the behaviour that we are going test
		repo.deleteById(student.getId());
		Optional<Student> studentOptional = repo.findById(student.getId());

		// then - verify the output
		assertThat(studentOptional).isEmpty();
	}
}
