package com.alvion.alviontask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvion.alviontask.jparepos.StudentRepo;
import com.alvion.alviontask.model.Student;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private StudentRepo db;

	@Autowired
	public StudentController(StudentRepo db) {
		this.db = db;
	}

	@PostMapping
	@RequestMapping("/add")
	public void addStudent(@RequestBody Student student) {
		db.save(student);
	}
	
	@PostMapping
	@RequestMapping("/remove")
	public void removeStudent(@RequestBody Student student) {
		db.delete(student);
	}
	
	@GetMapping
	@RequestMapping("/all")
	public List<Student> getStudents() {
		return db.findAll();
	}
}
