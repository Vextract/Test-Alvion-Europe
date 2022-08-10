package com.alvion.alviontask.jparepos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.alvion.alviontask.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long>{

}
