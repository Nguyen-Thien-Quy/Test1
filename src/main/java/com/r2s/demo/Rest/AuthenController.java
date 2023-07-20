package com.r2s.demo.Rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.AuthenDTO;
import com.r2s.demo.DTO.AuthenDTOResponse;
import com.r2s.demo.Utils.JwtUltils;
import com.r2s.demo.constant.ResponseCode;

@RestController
@RequestMapping("/login")
public class AuthenController extends BaseRestController{
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@PostMapping()
	public ResponseEntity<?> login(@RequestBody AuthenDTO authen) {
		try {
			
			//String enCoderPassword = this.passwordEncoder.encode(authen.getPassword());
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authen.getUserName(), authen.getPassword()));
			
			String token = JwtUltils.generateToken(authen.getUserName());
			AuthenDTOResponse response = new AuthenDTOResponse(token, "Dang Nhap Thanh Cong!");
			return super.success(response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());

	}
}
