package com.altimetrik.stackoverflow.utils;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.altimetrik.stackoverflow.entity.Users;
import com.altimetrik.stackoverflow.enums.Constants;
import com.altimetrik.stackoverflow.exception.AuthenticationException;
import com.altimetrik.stackoverflow.repository.UsersRepository;

@Component
public class UsersValidation {
	
	@Autowired
	private UsersRepository usersRepository;
	
	public void validateUserAccess(Map<String, String> requestHeader) {
		validateUserAccess(requestHeader);
		String userId = JwtUtil.getStaffIdFromJwtToken(requestHeader.get(Constants.X_E2E_TRUST_TOKEN.toLowerCase()));
		Optional<Users> user = usersRepository.findById(Long.parseLong(userId));
		if(!user.isPresent()) {
			throw new AuthenticationException("User doesn't exist.", HttpStatus.BAD_REQUEST, Constants.WORKFLOW_XAPI, new Date()); 
		}
	}
}
