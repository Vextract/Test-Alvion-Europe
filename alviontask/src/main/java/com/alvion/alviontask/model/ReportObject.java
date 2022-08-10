package com.alvion.alviontask.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReportObject {

	private String professorsName;
	private int numberOfStudents;
	private float averageGradeOfStudents;
	
}
