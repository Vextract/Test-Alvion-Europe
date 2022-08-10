package com.alvion.alviontask.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
@AllArgsConstructor
public class CourseStudying {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="course_id", referencedColumnName = "number")
	private Course course;
	private float summaryOfGrades;
	private Integer numberOfGrades;
	private float currentAverageGrade;
	private Integer finalGrade;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id", referencedColumnName = "numberOfRecordBook")
	private Student student;
	
	public CourseStudying(Course course, Integer summaryOfGrades, Integer numberOfGrades, float currentAverageGrade,
			Student student) {
		this.course = course;
		this.summaryOfGrades = summaryOfGrades;
		this.numberOfGrades = numberOfGrades;
		this.currentAverageGrade = currentAverageGrade;
		this.student = student;
	}

	public float getCurrentAverageGrade() {
		if (numberOfGrades > 0) {
			this.currentAverageGrade = summaryOfGrades / numberOfGrades;
		} else {
			this.currentAverageGrade = 0;
		}
		return this.currentAverageGrade;
	}

	public void setCurrentAverageGrade(float currentAverageGrade) {
		this.currentAverageGrade = currentAverageGrade;
	}

	public void addGradeAndUpdate(Integer grade) {
		this.numberOfGrades++;
		this.summaryOfGrades += grade;
	}
}
