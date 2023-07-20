package com.r2s.demo.Respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.r2s.demo.Model.User;

@Repository
public interface UserRespository extends JpaRepository<User, Long> {
	Optional<User> findByName(String name);
	
	//Tim kiem ten gan dung
	//su dung contains
	List<User> findByNameContaining(String name);
	
	//Su dung like pattern, regex
	List<User> findByNameLike(String name);
}
