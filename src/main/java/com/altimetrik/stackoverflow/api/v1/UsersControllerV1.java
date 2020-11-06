package com.altimetrik.stackoverflow.api.v1;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.altimetrik.stackoverflow.entity.Users;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.service.UsersService;

@RestController
@RequestMapping("/api/v1")
public class UsersControllerV1 {
	
	@Autowired
	private UsersService usersService;
	
	@GetMapping(path="/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Users> getUserById(@PathVariable final Long id, @RequestHeader final Map<String, String> requestHeader){
		Optional<Users> user = usersService.getUserById(id);
		if(user!=null && user.isPresent()) {
			return new ResponseEntity<>(user.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> createUserDetails(@RequestBody final Users user, @RequestHeader final Map<String, String> requestHeader){
		Response response = usersService.createUserDetails(user);
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response = new Response();
			response.setMessage("User Not Added");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PutMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateUserDetails(@RequestBody final Users user, @RequestHeader final Map<String, String> requestHeader){
		Response response = usersService.updateUserDetails(user);
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/users", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteUserDetails(@RequestBody final Users user, @RequestHeader final Map<String, String> requestHeader){
		Response response = usersService.deleteUserDetails(user.getId());
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setMessage("User Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
