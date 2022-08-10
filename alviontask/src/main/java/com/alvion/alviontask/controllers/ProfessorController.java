package com.alvion.alviontask.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alvion.alviontask.jparepos.ProfessorRepo;
import com.alvion.alviontask.model.Professor;

@RestController
@RequestMapping("/professors")
public class ProfessorController {

	private ProfessorRepo db;

	@Autowired
	public ProfessorController(ProfessorRepo db) {
		this.db = db;
	}
	
	@PostMapping
	@RequestMapping("/add")
	public void addProfessor(@RequestBody Professor professor) {
		db.save(professor);
	}
	
	@PostMapping
	@RequestMapping("/remove")
	public void removeProfessor(@RequestBody Professor professor) {
		db.delete(professor);
	}
	
	@GetMapping
	@RequestMapping("/all")
	public List<Professor> getProfessors() {
		return db.findAll();
	}
}