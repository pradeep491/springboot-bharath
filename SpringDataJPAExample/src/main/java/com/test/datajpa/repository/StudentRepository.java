package com.test.datajpa.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.datajpa.entitites.Student;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
