package com.altimetrik.stackoverflow.api.v1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.StandaloneMockMvcBuilder;

import com.altimetrik.stackoverflow.entity.Posts;
import com.altimetrik.stackoverflow.enums.Constants;
import com.altimetrik.stackoverflow.enums.PostType;
import com.altimetrik.stackoverflow.model.request.PostRequest;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.service.PostService;
import com.altimetrik.stackoverflow.service.UsersService;


public class PostsControllerV1Test {
	
	@SuppressWarnings("unused")
	private StandaloneMockMvcBuilder mockMvcBuilder;

	@InjectMocks
	private PostsControllerV1 postsController;
	
	@Mock
	private UsersService usersService;
	
	@Mock 
	private PostService postService;
	
	@Mock
	Map<String, String> headers;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvcBuilder = MockMvcBuilders.standaloneSetup(this.postsController);
	}
	
	@Test
	public void testGetPostById() {
		headers.put(Constants.CONTENT_TYPE, "application/json");
		headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		Long userId = new Long(1);
		Long postId = new Long(1);
		Mockito.when(usersService.getUserById(userId)).thenReturn(null);
		Mockito.when(postService.getPostById(postId)).thenReturn(null);
		
		//Execute
		ResponseEntity<Posts> postDetails = postsController.getPostById(postId, headers);
		
		//Verify
		assertNotNull(postDetails);
	}
	
	 @Test
	 public void testAddPost() throws Exception {
		 headers.put(Constants.CONTENT_TYPE, "application/json");
		 headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		 PostRequest postStub = buildPost();
		 Response responseStub = new Response();
		 responseStub.setMessage("Post Added successfully");
		 when(postService.createPostDetails(postStub)).thenReturn(responseStub);
		 
		//Execute
		ResponseEntity<Response> postDetails = postsController.createPostDetails(postStub, headers);
		
	    // verify
	    assertEquals(responseStub.getMessage(), postDetails.getBody().getMessage(), "Post Added successfully");
	 }
	 
	 @Test
	 public void testModifyPost() {
		 headers.put(Constants.CONTENT_TYPE, "application/json");
		 headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		 PostRequest postStub = buildPost();
		 Response responseStub = new Response();
		 responseStub.setMessage("Post Added successfully");
		 when(postService.updatePostDetails(postStub)).thenReturn(responseStub);
		 
		//Execute
		 ResponseEntity<Response> postDetails = postsController.updatePostDetails(postStub, headers);
		
	    // verify
	    assertEquals(responseStub.getMessage(), postDetails.getBody().getMessage(), "Post updated successfull!!");
	 }
	 
	 @Test
	 public void deletePost() {
		 headers.put(Constants.CONTENT_TYPE, "application/json");
		 headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		 Posts postStub = new Posts();
		 postStub.setId(new Long(1));
		 Response responseStub = new Response();
		 responseStub.setMessage("Post deleted successfull!!");
		 when(postService.deletePostDetails(new Long(1))).thenReturn(responseStub);
		 
		 //Execute
		 ResponseEntity<Response> postDetails = postsController.deletePostDetails(postStub, headers);
		
	    // verify
	    assertEquals(responseStub.getMessage(), postDetails.getBody().getMessage(), "Post deleted successfull!!");
	 }
	 
	 private PostRequest buildPost() { 
		 PostRequest post = new PostRequest();
		 post.setUserId(new Long(1));
		 post.setTitle("Test Post Q1");
		 post.setPostType(PostType.QUESTION);
		 return post;
	 }
}
