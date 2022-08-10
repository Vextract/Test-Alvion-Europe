package com.alvion.alviontask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alvion.alviontask.model.ReportObject;
import com.alvion.alviontask.services.Service;

@RestController
@RequestMapping("/api")
public class Controller {

	private Service service;

	@Autowired
	public Controller(Service service) {
		this.service = service;
	}
	
	@PostMapping
	@RequestMapping("/addtocourse")
	public void addNewStudentToCourse(@RequestParam("course") Long courseNumber, 
			@RequestParam("student") Long bookNumber) {
		service.addStudentToCourse(courseNumber, bookNumber);
	}
	
	@PostMapping
	@RequestMapping("/removefromcourse")
	public void removeStudentFromCourse(@RequestParam("course") Long courseNumber, 
			@RequestParam("student") Long bookNumber) {
		service.removeStudentFromCourse(courseNumber, bookNumber);
	}
	
	@PostMapping
	@RequestMapping("/assignprofessor")
	public void assignProfessorOnCourse(@RequestParam("id") Long id, @RequestParam("course") Long courseNumber) {
		service.assignProfessorOnCourse(id, courseNumber);
	}
	
	@GetMapping
	@RequestMapping("/generatexlsx")
	public void generateExcelFile() {
		List<ReportObject> objects = service.gatherAndCreateDataForReport();
		service.generateExcelFile(objects);
	}
	
}
