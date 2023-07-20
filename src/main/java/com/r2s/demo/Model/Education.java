package com.r2s.demo.Model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Embeddable
class UserSubjectKey{
	@Column(name = "user_id")
	Long UserId;
	
	@Column(name = "subject_id")
	Long SubjectId;
}

@Entity
@Table(name = "Education")
public class Education {
//	@EmbeddedId
//	private UserSubjectKey id;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	//@MapsId("user_id")
	@JoinColumn(name = "userId")
	@JsonBackReference
	private User user;
	
	@ManyToOne
	//@MapsId("subject_id")
	@JoinColumn(name = "subjectId")
	@JsonBackReference
	private Subject subject;
	
	private double mark;
	
	private String semester;
	
	private Date date;

	@Column(columnDefinition = "boolean default false")
	private boolean isDeleted;

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
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

	public Education() {
		super();
	}

	public Education(Long id, User user, Subject subject, double mark, String semester) {
		super();
		this.id = id;
		this.user = user;
		this.subject = subject;
		this.mark = mark;
		this.semester = semester;
	}
}
