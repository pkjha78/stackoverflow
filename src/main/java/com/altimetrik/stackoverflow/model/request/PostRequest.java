package com.altimetrik.stackoverflow.model.request;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.altimetrik.stackoverflow.entity.Tags;
import com.altimetrik.stackoverflow.enums.PostType;

import lombok.Data;

@Data
public class PostRequest {
	private Long id;
	private Long userId;
    private Set<Long> tagsId;
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
	private LocalDateTime lastEditDate;
	private LocalDateTime lastActivityDate;
	private LocalDateTime closedDate;
	private LocalDateTime communityOwnedDate;
}
