package com.altimetrik.stackoverflow.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.altimetrik.stackoverflow.entity.Users;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.repository.UsersRepository;

@Service
public class UsersService {
	
	@Autowired
	private UsersRepository usersRepository;
	
	
	public Optional<Users> getUserById(Long userId) {
		return usersRepository.findById(userId);
	}
	
	public Optional<Users> getUserByDisplayName(String displayName) {
		return usersRepository.findByDisplayName(displayName);
	}
	
	public List<Users> getAllUsers(){
		return usersRepository.findAll();
	}
	
	public Response createUserDetails(Users user) {
		Response response = null;
		//Optional<Users> userObj = usersRepository.findById(user.getId());
		//if(!userObj.isPresent()) {
			usersRepository.save(user);
			response = new Response();
			response.setMessage("User Added successfully");
		//}
		return response;
	}
	
	public Response updateUserDetails(Users user) {
		Response response = null;
		Optional<Users> userObj = usersRepository.findById(user.getId());
		if(userObj.isPresent()) {
			usersRepository.save(user);
			response = new Response();
			response.setMessage("User updated successfull!!");
		}
		return response;
	}
	
	public Response deleteUserDetails(Long userId) {
		Response response = null;
		Optional<Users> userObj = usersRepository.findById(userId);
		if(userObj.isPresent()) {
			usersRepository.delete(userObj.get());
			response = new Response();
			response.setMessage("User deleted successfull!!");
		}
		return response;
	}
}
