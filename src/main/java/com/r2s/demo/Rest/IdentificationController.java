package com.r2s.demo.Rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.Model.Identification;
import com.r2s.demo.Respository.IdentificationResponsitory;
import com.r2s.demo.constant.ResponseCode;

@RestController
@RequestMapping(path = "/identification")
public class IdentificationController extends BaseRestController{
	@Autowired
	private IdentificationResponsitory identificationResponsitory;
	
	@GetMapping("")
	public ResponseEntity<?> getAllIdentification(){
		try {
			List<Identification> identifications = this.identificationResponsitory.findAll();
			return super.success(identifications);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
}
