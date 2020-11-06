package com.altimetrik.stackoverflow.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Data
@Table(name = "comments", schema = "stackoverflow")
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "users_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty("users_id")
	private Users usersReference;
	
	private String userDisplayName;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "posts_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonProperty("posts_id")
	private Posts postsReference;
	
	private String text;
	
	private Long score;
	
	@Column(name = "creation_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime creationDate;
}
