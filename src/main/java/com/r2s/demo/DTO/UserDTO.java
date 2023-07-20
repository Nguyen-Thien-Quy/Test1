package com.r2s.demo.DTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.Model.User;
import com.r2s.demo.Model.Account;
import com.r2s.demo.Model.Education;

public class UserDTO {
	private Long id;
	private String name;
	private String address;
	private Date expiredDate;
	private List<Account> accounts;
	private List<Education> educations;
	
	public UserDTO(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.address = user.getAddress();
		if(!ObjectUtils.isEmpty(user.getIdentification())) {
			this.expiredDate = user.getIdentification().getExpiredDate();
		}
		this.accounts = user.getAccounts();
		if(!ObjectUtils.isEmpty(user.getEducations())) {
			this.educations  = user.getEducations();
		}
	}
	public List<Education> getEducations() {
		return educations;
	}
	public void setEducations(List<Education> educations) {
		this.educations = educations;
	}
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}
	
	
}
