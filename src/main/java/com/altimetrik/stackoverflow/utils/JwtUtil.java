package com.altimetrik.stackoverflow.utils;

import java.util.Date;
import java.util.Map;

import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import com.altimetrik.stackoverflow.enums.Constants;
import com.altimetrik.stackoverflow.exception.AuthenticationException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtUtil {

	public static String getStaffIdFromJwtToken(String token){
		String decodedToken = new String(new Base64(true).decode(token.split(Constants.SPLIT_CHAR)[1]));
		return (String) new JSONObject(decodedToken).get(Constants.SUBJECT);
	}
	
	public static String getStaffIdFromJWT(Map<String, String> requestHeader) {
		String staffId = null;
		try {
			staffId = JwtUtil.getStaffIdFromJwtToken(requestHeader.get(Constants.X_E2E_TRUST_TOKEN.toLowerCase()));
		} catch(Exception e) {
			log.error("Staff doesn't exist");
			throw new AuthenticationException("User doesn't exist. errMsg: " + e.getMessage(), HttpStatus.BAD_REQUEST, Constants.WORKFLOW_XAPI, new Date());
		}
		return staffId;
	}
}
