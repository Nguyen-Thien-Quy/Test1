package com.r2s.demo.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.Model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Long>{

}
