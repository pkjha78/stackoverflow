package com.altimetrik.stackoverflow.enums;

import lombok.Getter;

@Getter
public enum PostType {
	
	QUESTION("Question"), ANSWER("Answer");

	private String value;

	private PostType(String value) {
		this.value = value;
	}
}
