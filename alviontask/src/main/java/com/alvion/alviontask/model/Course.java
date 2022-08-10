package com.alvion.alviontask.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.utility.nullability.MaybeNull;

@Entity
@Data
@Table
@NoArgsConstructor
public class Course {

	private String title;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long number;
	private float price;
	
	// Список id студентов на курсе
	@ManyToMany
	@JoinTable(
			name="students_on_course",
			joinColumns = @JoinColumn(name = "course_id"),
			inverseJoinColumns = @JoinColumn(name = "student_id")
	)
	private List<Student> studentsOnCourse = new ArrayList<>();
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "professor_id", referencedColumnName = "id")
	@MaybeNull
	private Professor professor;
	
	public Course(String title, float price) {
		this.title = title;
		this.price = price;
	}

	public void addStudentToCourse(Student studentToAdd) {
		this.getStudentsOnCourse().add(studentToAdd);
		
	}
}