package com.r2s.demo.Respository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.r2s.demo.Model.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long>{

}
