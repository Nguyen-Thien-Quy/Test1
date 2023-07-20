package com.r2s.demo.DTO;

import java.util.Date;

import org.springframework.util.ObjectUtils;

import com.r2s.demo.Model.Education;

public class EducationDTO {
	private Long id;
	private Date date;
	private double mark;
	private String semester;
	private Long UserId;
	private String UserName;
	private Long SubjectId;
	private String SubjectName;
	
	public EducationDTO(Education education) {
		this.id  = education.getId();
		this.date = education.getDate();
		this.mark = education.getMark();
		this.semester = education.getSemester();
		
		if(!ObjectUtils.isEmpty(education.getUser())) {
			this.UserId = education.getUser().getId();
			this.UserName = education.getUser().getName();
		}
		
		if(!ObjectUtils.isEmpty(education.getSubject())) {
			this.SubjectId = education.getSubject().getId();
			this.SubjectName = education.getSubject().getSubject_Name();
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Long getUserId() {
		return UserId;
	}

	public void setUserId(Long userId) {
		UserId = userId;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public Long getSubjectId() {
		return SubjectId;
	}

	public void setSubjectId(Long subjectId) {
		SubjectId = subjectId;
	}

	public String getSubjectName() {
		return SubjectName;
	}

	public void setSubjectName(String subjectName) {
		SubjectName = subjectName;
	}
	
}
