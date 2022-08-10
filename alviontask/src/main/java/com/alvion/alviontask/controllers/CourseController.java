package com.alvion.alviontask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvion.alviontask.jparepos.CourseRepo;
import com.alvion.alviontask.model.Course;
import com.alvion.alviontask.services.Service;

@RestController
@RequestMapping("/courses")
public class CourseController {

	private CourseRepo courseRepo;
	private Service service;
	
	@Autowired
	public CourseController(CourseRepo courseRepo, Service service) {
		this.courseRepo = courseRepo;
		this.service = service;
	}

	@PostMapping
	@RequestMapping("/add")
	public void addCourse(@RequestBody Course course) {
		courseRepo.save(course);
	}
	
	@PostMapping
	@RequestMapping("/remove")
	public void removeCourse(@RequestBody Course course) {
		courseRepo.delete(course);
	}
	
	@GetMapping
	@RequestMapping("/all")
	public List<Course> getCourses() {
		return courseRepo.findAll();
	}
	
	@PostMapping
	@RequestMapping("/newGrades")
	public void giveGrades() {
		service.generateAndGiveGrades();
	}
}
