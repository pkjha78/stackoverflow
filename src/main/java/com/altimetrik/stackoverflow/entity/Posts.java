package com.altimetrik.stackoverflow.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.altimetrik.stackoverflow.enums.PostType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Entity
@Data
@Table(name = "posts", schema = "stackoverflow")
public class Posts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonIgnoreProperties
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "owner_users_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Users ownerUserReference;
	
	@JsonIgnoreProperties
	@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
            })
    @JoinTable(name = "post_tags",
            joinColumns = { @JoinColumn(name = "post_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") })
    private Set<Tags> tags = new HashSet<>();

	private String ownerDisplayName;

	private String title;

	private String body;

	@Enumerated(EnumType.STRING)
	private PostType postType;


	private Long score;
	private Long viewCount;
	private Long answerCount;
	private Long commnetCount;
	private Long favoriteCount;
	
	private Long parentId;
	private Long acceptedAnswerId;
	private Long lastEditorUserId;
	

	private String lastEditorDisplayName;

	@Column(name = "last_edit_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime lastEditDate;

	@Column(name = "last_activity_date", updatable = false)
	private LocalDateTime lastActivityDate;

	@Column(name = "closed_date", updatable = false)
	private LocalDateTime closedDate;

	@Column(name = "community_owned_date", updatable = false)
	@CreationTimestamp
	private LocalDateTime communityOwnedDate;

}
