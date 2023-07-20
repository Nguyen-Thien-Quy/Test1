package com.r2s.demo.Rest;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.UserDTO;
import com.r2s.demo.Model.User;
import com.r2s.demo.Respository.UserRespository;
import com.r2s.demo.Service.UserService;
import com.r2s.demo.constant.ResponseCode;


@RestController
@RequestMapping("/users")
public class UserController extends BaseRestController{
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRespository respository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@GetMapping("")
	public ResponseEntity<?> getUser(){
		try {
			List<User> users = this.userService.getAllUser();
			List<UserDTO> responses = users.stream().map(UserDTO::new).toList();
			// Dong  39 co the hieu la
//			List<UserDTO> responses = new ArrayList<UserDTO>();
//			for (User user : users) {
//				responses.add(new UserDTO(user));
//			}
			return super.success(responses);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
	
	@GetMapping("/getById")
	public ResponseEntity<?> getById(@RequestParam(name = "id") long id) {
		User foundUser = this.userService.findUserByID(id);
		if(ObjectUtils.isEmpty(foundUser)) {
			return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
		}
		
		return super.success(foundUser);
		
	}
	
	@GetMapping("/getByName")
	public ResponseEntity<?> getByName(@RequestParam(name = "name") String name) {
		List<User> listFoundUser = this.userService.findByName("%" + name + "%");
		if(ObjectUtils.isEmpty(listFoundUser)) {
			return new ResponseEntity<>("Khong co gia tri tim thay", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(listFoundUser, HttpStatus.OK);
		
	}
	
//	
//	@GetMapping("/{id}")
//	public User getId(@PathVariable long id) {
//		User foundUser = null;
//		for (User user : users) {
//			if(user.getId().equals(id)) {
//				foundUser = user;
//			}
//		}
//		return foundUser;
//	}
//	
	@PutMapping("/{id}")
	public ResponseEntity<?> UpdateUser(@PathVariable long id, @RequestBody Map<String, Object> newUser) {
		if(ObjectUtils.isEmpty(newUser)) {
			return super.eror(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMassage());
		}
		
		if(ObjectUtils.isEmpty(newUser.get("name")) || ObjectUtils.isEmpty(newUser.get("password")) ||
				ObjectUtils.isEmpty(newUser.get("address"))) {
			return super.eror(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMassage());
		}
		
		return super.success(this.userService.updateUser(id, newUser));
	}
	
	@PreAuthorize("hasRole('Admin')")
	@PostMapping("")
	public ResponseEntity<?> postUser(@RequestBody(required = false) Map<String, Object> newUser) {
		
		try {
			if(ObjectUtils.isEmpty(newUser)) {
				return super.eror(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMassage());
			}
			
			if(ObjectUtils.isEmpty(newUser.get("name")) || ObjectUtils.isEmpty(newUser.get("password")) ||
					ObjectUtils.isEmpty(newUser.get("address"))) {
				return super.eror(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMassage());
			}
			User foundUser = this.respository.findByName(newUser.get("name").toString()).orElse(null);
			
			User user =  this.userService.addNewUser(newUser);
			if(!ObjectUtils.isEmpty(user)) {
				return super.success(new UserDTO(user));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMassage());
	}
	
	@DeleteMapping("/deleteByID")
	public ResponseEntity<?> removeUser(@RequestParam(name = "id") long id) {
		try {
			User foundUser = this.respository.findById(id).orElse(null);
			
			if(ObjectUtils.isEmpty(foundUser)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}			
			this.userService.removeUser(id);
			
			return super.success(foundUser);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
	
}
