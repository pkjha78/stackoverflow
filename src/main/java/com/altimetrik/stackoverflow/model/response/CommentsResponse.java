package com.altimetrik.stackoverflow.model.response;

import lombok.Data;

@Data
public class CommentsResponse {
	private Long id;
	private Long userId;
	private String userDisplayName;
	private Long postId;
	private String text;
	private Long score;
	private String creationDate;
}
