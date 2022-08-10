package com.alvion.alviontask.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
public class Student {
	
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long numberOfRecordBook;
	private float averageGrade;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "studentsOnCourse")
	private List<Course> courses = new ArrayList<>();
	@JsonIgnore
	@OneToMany(mappedBy = "student")
	private List<CourseStudying> coursesProgress = new ArrayList<>();
	
	public Student(String name, String address, String phoneNumber, String email) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public float calculateAverageGrade() {
		float summaryOfGrades = 0;
		for (CourseStudying course:coursesProgress) {
			if (course.getFinalGrade() == null || course.getFinalGrade() == 0) {
				summaryOfGrades += course.getCurrentAverageGrade();
			} else {
				summaryOfGrades += course.getFinalGrade();
			}
		}
		this.averageGrade = summaryOfGrades / this.coursesProgress.size();
		return this.averageGrade;
	}
}

