package com.r2s.demo.Rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.AccountDTO;
import com.r2s.demo.Model.Account;
import com.r2s.demo.Model.User;
import com.r2s.demo.Respository.AccountRepository;
import com.r2s.demo.Respository.UserRespository;
import com.r2s.demo.constant.ResponseCode;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController extends BaseRestController{
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private UserRespository  userRespository;
	
	@GetMapping
	public ResponseEntity<?> getAllAcount(){
		try {
			List<Account> accounts = this.accountRepository.findAll();
			List<AccountDTO> responses = accounts.stream().map(AccountDTO::new).toList();
			
			return super.success(responses);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<?> postAccount(@PathVariable long id, @RequestBody Map<String, Object> account){
		try {
			if(ObjectUtils.isEmpty(account)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			//long userId = Long.parseLong(account.get("userId").toString());
			User foundUser = this.userRespository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundUser)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			
			Account newAccount = new Account();
			newAccount.setBalance(Double.parseDouble(account.get("balance").toString()));
			newAccount.setUser(foundUser);
			
			this.accountRepository.save(newAccount);
//			foundUser.getAccounts().add(newAccount);
//			this.userRespository.save(foundUser);
			return super.success(newAccount);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> upddateAccount(@PathVariable long id, @RequestBody Map<String, Object> account){
		try {
			Account foundAccount = this.accountRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundAccount)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			if(ObjectUtils.isEmpty(account)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			foundAccount.setBalance(Double.parseDouble(account.get("balance").toString()));
			return super.success(this.accountRepository.save(foundAccount));			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> removeAccount(@PathVariable long id){
		try {
			Account foundAccount = this.accountRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundAccount)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			
			foundAccount.setUser(null);
			this.accountRepository.deleteById(id);
			return super.success(foundAccount);			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
}
