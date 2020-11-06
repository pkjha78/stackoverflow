package com.altimetrik.stackoverflow.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name = "users", schema = "stackoverflow")
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String displayName;
	private String emailHash;
	private Integer age;
	private String accountId;
	private String location;
	private String aboutMe;
	private String profileImageUrl;
	private String websiteUrl;
	private Long views;
	private Long upVotes;
	private Long downVotes;
	
	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime creationDate;
	
	@Column(name = "last_access_date", updatable = true)
	@UpdateTimestamp
	private LocalDateTime lastAccessDate;
}
