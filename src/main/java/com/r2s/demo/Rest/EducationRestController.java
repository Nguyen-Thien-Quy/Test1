package com.r2s.demo.Rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.r2s.demo.DTO.EducationDTO;
import com.r2s.demo.Model.Education;
import com.r2s.demo.Model.Subject;
import com.r2s.demo.Model.User;
import com.r2s.demo.Respository.EducationRepository;
import com.r2s.demo.Respository.SubjectRepository;
import com.r2s.demo.Respository.UserRespository;
import com.r2s.demo.constant.ResponseCode;

@RestController
@RequestMapping("/educations")
public class EducationRestController extends BaseRestController{
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private  UserRespository userRespository;
	
	@GetMapping
	public ResponseEntity<?> getAllEducation(Pageable pageable){
		try {
			
			List<Education> educations = this.educationRepository.findAll(pageable).stream().map(x->x).toList();
			List<EducationDTO> responses = educations.stream().map(EducationDTO::new).toList();
			
			return super.success(responses);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}

	@PostMapping
	@Transactional(noRollbackFor = {Exception.class})
	public ResponseEntity<?> AddUser(@RequestBody Map<String, Object> education){
		try {
			if(ObjectUtils.isEmpty(education) || ObjectUtils.isEmpty(education.get("userId")) 
					|| ObjectUtils.isEmpty(education)) {
				return super.eror(ResponseCode.NO_PARAM.getCode(), ResponseCode.NO_PARAM.getMassage());
			}
			
			Long userId = Long.parseLong(education.get("userId").toString());
			User foundUser = this.userRespository.findById(userId).orElse(null);		
			if(ObjectUtils.isEmpty(foundUser)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			
			Long subjectId = Long.parseLong(education.get("subjectId").toString());
			Subject foundSubject = this.subjectRepository.findById(subjectId).orElse(null);
			if(ObjectUtils.isEmpty(foundSubject)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			
			Education newEducation = new Education();
			newEducation.setUser(foundUser);
			newEducation.setSubject(foundSubject);
			newEducation.setSemester(education.get("semester").toString());
			newEducation.setDate(foundUser.getIdentification().getExpiredDate());
			
			return super.success(this.educationRepository.save(newEducation));
		} catch (Exception e) {
			// TODO: handle exception	
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEducation(@PathVariable long id){
		try {
			Education foundEducation = this.educationRepository.findById(id).orElse(null);
			if(ObjectUtils.isEmpty(foundEducation)) {
				return super.eror(ResponseCode.NOT_FOUND.getCode(), ResponseCode.NOT_FOUND.getMassage());
			}
			
			foundEducation.setDeleted(true);
			this.educationRepository.save(foundEducation);
			return super.success(foundEducation);		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return super.eror(ResponseCode.NO_CONTENT.getCode(), ResponseCode.NO_CONTENT.getMassage());
	}
}
