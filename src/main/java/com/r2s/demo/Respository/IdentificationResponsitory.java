package com.r2s.demo.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.Model.Identification;

@Repository
public interface IdentificationResponsitory extends JpaRepository<Identification, Long>{

}
