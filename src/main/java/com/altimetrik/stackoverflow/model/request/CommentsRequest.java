package com.altimetrik.stackoverflow.model.request;

import lombok.Data;

@Data
public class CommentsRequest {
	private Long id;
	private Long userId;
	private Long postId;
	private String text;
	private Long score;
}