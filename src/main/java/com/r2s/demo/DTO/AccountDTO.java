package com.r2s.demo.DTO;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.Model.Account;
import com.r2s.demo.Model.User;

public class AccountDTO {
	private Long userId;
	private String userName;
	private String userAddress;
	private long id;
	private double balance;
	
	public AccountDTO(Account account) {
		// TODO Auto-generated constructor stub
		this.id = account.getId();
		this.balance = account.getBalance();
		if(!ObjectUtils.isEmpty(account.getUser())) {
			User user = account.getUser();
			this.userId = user.getId();
			this.userName = user.getName();
			this.userAddress = user.getAddress();
		}
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
