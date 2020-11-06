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

import com.altimetrik.stackoverflow.model.request.CommentsRequest;
import com.altimetrik.stackoverflow.model.response.CommentsResponse;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.service.CommentsService;
import com.altimetrik.stackoverflow.utils.UsersValidation;


@RestController
@RequestMapping("/api/v1")
public class CommentsControllerV1 {

	@Autowired
	private CommentsService commentsService;
	
	@Autowired
	private UsersValidation userValidation;
	
	@GetMapping(path="/comments/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CommentsResponse> getCommentById(@PathVariable final Long id, @RequestHeader final Map<String, String> requestHeader){
		
		userValidation.validateUserAccess(requestHeader);
		
		Optional<CommentsResponse> comment = commentsService.getCommentById(id);
		if(comment!=null && comment.isPresent()) {
			return new ResponseEntity<>(comment.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path="/comments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> createCommentDetails(@RequestBody final CommentsRequest comment, @RequestHeader final Map<String, String> requestHeader){
		userValidation.validateUserAccess(requestHeader);
		
		Response response = commentsService.createCommentDetails(comment);
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response = new Response();
			response.setMessage("Comment Not Added");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PutMapping(path="/comments", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updateCommentDetails(@RequestBody final CommentsRequest comment, @RequestHeader final Map<String, String> requestHeader){
		userValidation.validateUserAccess(requestHeader);
		Response response = commentsService.updateCommentDetails(comment);
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setMessage("Comment Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/comments", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deleteCommentDetails(@RequestBody final CommentsRequest comment, @RequestHeader final Map<String, String> requestHeader){
		userValidation.validateUserAccess(requestHeader);
		Response response = commentsService.deleteCommentDetails(comment.getId());
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setMessage("Comment Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
