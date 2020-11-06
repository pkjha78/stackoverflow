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

import com.altimetrik.stackoverflow.entity.Posts;
import com.altimetrik.stackoverflow.model.request.PostRequest;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.service.PostService;

@RestController
@RequestMapping("/api/v1")
public class PostsControllerV1 {
	
	@Autowired
	private PostService postService;

	@GetMapping(path="/posts/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Posts> getPostById(@PathVariable final Long id, @RequestHeader final Map<String, String> requestHeader){
		Optional<Posts> post = postService.getPostById(id);
		if(post!=null && post.isPresent()) {
			return new ResponseEntity<>(post.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> createPostDetails(@RequestBody final PostRequest post, @RequestHeader final Map<String, String> requestHeader){
		Response response = postService.createPostDetails(post);
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.CREATED);
		} else {
			response = new Response();
			response.setMessage("Post Not Added");
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
	
	@PutMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> updatePostDetails(@RequestBody final PostRequest post, @RequestHeader final Map<String, String> requestHeader){
		Response response = postService.updatePostDetails(post);
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setMessage("Post Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping(path="/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Response> deletePostDetails(@RequestBody final Posts post, @RequestHeader final Map<String, String> requestHeader){
		Response response = postService.deletePostDetails(post.getId());
		if(response!=null) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			response = new Response();
			response.setMessage("Post Not Found");
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
