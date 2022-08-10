package com.alvion.alviontask;

import java.util.List;
import java.util.Random;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.alvion.alviontask.controllers.Controller;
import com.alvion.alviontask.controllers.CourseController;
import com.alvion.alviontask.jparepos.CourseRepo;
import com.alvion.alviontask.jparepos.ProfessorRepo;
import com.alvion.alviontask.jparepos.StudentRepo;
import com.alvion.alviontask.model.Course;
import com.alvion.alviontask.model.Professor;
import com.alvion.alviontask.model.Student;

@SpringBootApplication
public class AlviontaskApplication {

	private Random r = new Random();

	public static void main(String[] args) {
		SpringApplication.run(AlviontaskApplication.class, args);
	}

	
	  @Bean CommandLineRunner commandLineRunner(CourseRepo courseRepo,
	  ProfessorRepo professorRepo, StudentRepo studentRepo, Controller controller,
	  CourseController controller2) { return args -> {
	  
				
				
				
				/*
				 * Первый блок - сущности 
				 * Второй блок - внедрение зависимостей между сущностями
				 * Третий - удаление зависимостей, заполнение данными (средние оценки)
				 */
		  
				/*
				 * Course course = new Course("Math", 20 + 20 + r.nextFloat() * 80); Course
				 * course1 = new Course("English", 20 + r.nextFloat() * 80); Course course2 =
				 * new Course("Russian", 20 + r.nextFloat() * 80); Course course3 = new
				 * Course("Geography", 20 + r.nextFloat() * 80); Course course4 = new
				 * Course("Physics", 20 + r.nextFloat() * 80); Course course5 = new
				 * Course("First Aid", 20 + r.nextFloat() * 80); Course course6 = new
				 * Course("Whatever", 20 + r.nextFloat() * 80);
				 * courseRepo.saveAll(List.of(course, course1, course2, course3, course4,
				 * course5, course6));
				 * 
				 * 
				 * Professor professor = new Professor("Some professor name1", "address",
				 * "phoneNumber", 10000 + r.nextFloat() * 1000); Professor professor1 = new
				 * Professor("Some professor name2", "address", "phoneNumber", 10000 +
				 * r.nextFloat() * 1000); Professor professor2 = new
				 * Professor("Some professor name3", "address", "phoneNumber", 10000 +
				 * r.nextFloat() * 1000); professorRepo.saveAll(List.of(professor, professor1,
				 * professor2)); Student student = new Student("studentName1", "address",
				 * "phoneNumber", "email"); Student student1 = new Student("studentName2",
				 * "address", "phoneNumber", "email"); Student student2 = new
				 * Student("studentName3", "address", "phoneNumber", "email"); Student student3
				 * = new Student("studentName4", "address", "phoneNumber", "email"); Student
				 * student4 = new Student("studentName5", "address", "phoneNumber", "email");
				 * Student student5 = new Student("studentName6", "address", "phoneNumber",
				 * "email"); Student student6 = new Student("studentName7", "address",
				 * "phoneNumber", "email"); Student student7 = new Student("studentName8",
				 * "address", "phoneNumber", "email"); Student student8 = new
				 * Student("studentName9", "address", "phoneNumber", "email"); Student student9
				 * = new Student("studentName10", "address", "phoneNumber", "email"); Student
				 * student10 = new Student("studentName11", "address", "phoneNumber", "email");
				 * studentRepo.saveAll(List.of(student, student1, student2, student3, student4,
				 * student5, student6, student7, student8, student9, student10));
				 */
				  
				  
				  
				  
				  
				  
				  
				  
				  
				
				/*
				 * controller.addNewStudentToCourse(1L, 11L);
				 * controller.addNewStudentToCourse(1L, 12L);
				 * controller.addNewStudentToCourse(1L, 13L);
				 * controller.addNewStudentToCourse(1L, 14L);
				 * controller.addNewStudentToCourse(1L, 15L);
				 * controller.addNewStudentToCourse(2L, 16L);
				 * controller.addNewStudentToCourse(2L, 17L);
				 * controller.addNewStudentToCourse(3L, 18L);
				 * controller.addNewStudentToCourse(3L, 19L);
				 * controller.addNewStudentToCourse(3L, 20L);
				 * 
				 * 
				 * 
				 * controller.assignProfessorOnCourse(8L, 1L);
				 * controller.assignProfessorOnCourse(9L, 2L);
				 * controller.assignProfessorOnCourse(10L, 3L);
				 */
				 
				 
				 
				 
				 
				  
				  
				  
				/*
				 * controller2.giveGrades(); controller2.giveGrades(); controller2.giveGrades();
				 * controller.removeStudentFromCourse(1L, 11L);
				 * controller.removeStudentFromCourse(2L, 17L);
				 * controller.removeStudentFromCourse(3L, 20L);
				 * controller.addNewStudentToCourse(3L, 11L);
				 * controller.addNewStudentToCourse(1L, 17L);
				 * controller.addNewStudentToCourse(2L, 20L);
				 */
				 
	  
	  
	  
	  
	  
	  
	  };
	  
	  }
	 
}
