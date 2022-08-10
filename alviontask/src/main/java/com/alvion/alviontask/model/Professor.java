package com.alvion.alviontask.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table
@NoArgsConstructor
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String phoneNumber;
	private float payment;
	// Список номеров курсов, на которых преподаёт профессор.
	@JsonIgnore
	@OneToMany(mappedBy = "professor")
	private List<Course> courses = new ArrayList<>();
	
	public Professor(String name, String address, String phoneNumber, float payment) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.payment = payment;
	}
}
