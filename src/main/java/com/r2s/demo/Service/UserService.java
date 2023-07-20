package com.r2s.demo.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.r2s.demo.Model.Identification;
import com.r2s.demo.Model.User;
import com.r2s.demo.Respository.RoleRepository;
import com.r2s.demo.Respository.UserRespository;

@Service
public class UserService {
	@Autowired
	private UserRespository userRespository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	public List<User> getAllUser(){
		return this.userRespository.findAll();
	}
	
	public User findUserByID(long id) {
		//Optional: đảm bảo cho việc không trả về nullpoiter
		return this.userRespository.findById(id).orElse(null);
	}
	
	public User addNewUser(Map<String, Object> newUser) {
		User user  = new User();
		user.setName(newUser.get("name").toString());
		user.setAddress(newUser.get("address").toString());
		user.setPassword(this.passwordEncoder.encode(newUser.get("password").toString()));
		user.setDisplayName(newUser.get("displayName").toString());
		user.setRoles(this.roleRepository.findByRoleName("User"));
		
		Identification identification = new Identification();
		identification.setExpiredDate(new Date());
		
		user.setIdentification(identification);
		return this.userRespository.save(user);
	}
	
	public User updateUser(long id, Map<String, Object> newUser) {
		
		User user  = findUserByID(id);
		user.setName(newUser.get("name").toString());
		user.setAddress(newUser.get("address").toString());
		user.setPassword(this.passwordEncoder.encode(newUser.get("password").toString()));
		//user.setId(id);
			
		return this.userRespository.save(user);
	}
	
	public void removeUser(long id) {
		this.userRespository.deleteById(id);
	}
	
	public List<User> findByName(String name){
		return this.userRespository.findByNameLike(name);
	}
}
