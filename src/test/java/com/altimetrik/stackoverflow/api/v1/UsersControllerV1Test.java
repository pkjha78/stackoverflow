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

import com.altimetrik.stackoverflow.entity.Users;
import com.altimetrik.stackoverflow.enums.Constants;
import com.altimetrik.stackoverflow.model.response.Response;
import com.altimetrik.stackoverflow.service.UsersService;


public class UsersControllerV1Test {
	
	@SuppressWarnings("unused")
	private StandaloneMockMvcBuilder mockMvcBuilder;

	@InjectMocks
	private UsersControllerV1 usersController;
	
	@Mock
	private UsersService usersService;
	
	@Mock
	Map<String, String> headers;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		this.mockMvcBuilder = MockMvcBuilders.standaloneSetup(this.usersController);
	}
	
	@Test
	public void testGetUserById() {
		headers.put(Constants.CONTENT_TYPE, "application/json");
		headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		Long userId = new Long(1);
		Mockito.when(usersService.getUserById(userId)).thenReturn(null);
		
		//Execute
		ResponseEntity<Users> userDetails = usersController.getUserById(userId, headers);
		
		//Verify
		assertNotNull(userDetails);
	}
	
	 @Test
	 public void testAddUser() throws Exception {
		 headers.put(Constants.CONTENT_TYPE, "application/json");
		 headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		 Users userStub = buildUser();
		 Response responseStub = new Response();
		 responseStub.setMessage("User Added successfully");
		 when(usersService.createUserDetails(userStub)).thenReturn(responseStub);
		 
		//Execute
		ResponseEntity<Response> userDetails = usersController.createUserDetails(userStub, headers);
		
	    // verify
	    assertEquals(responseStub.getMessage(), userDetails.getBody().getMessage(), "User Added successfully");
	 }
	 
	 @Test
	 public void testModifyUser() {
		 headers.put(Constants.CONTENT_TYPE, "application/json");
		 headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		 Users userStub = buildUser();
		 Response responseStub = new Response();
		 responseStub.setMessage("User Added successfully");
		 when(usersService.updateUserDetails(userStub)).thenReturn(responseStub);
		 
		//Execute
		ResponseEntity<Response> userDetails = usersController.updateUserDetails(userStub, headers);
		
	    // verify
	    assertEquals(responseStub.getMessage(), userDetails.getBody().getMessage(), "User updated successfull!!");
	 }
	 
	 @Test
	 public void deleteUser() {
		 headers.put(Constants.CONTENT_TYPE, "application/json");
		 headers.put(Constants.X_E2E_TRUST_TOKEN, "e2e-token");
		 Users userStub = new Users();
		 userStub.setId(new Long(1));
		 Response responseStub = new Response();
		 responseStub.setMessage("Post deleted successfull!!");
		 when(usersService.deleteUserDetails(new Long(1))).thenReturn(responseStub);
		 
		 //Execute
		 ResponseEntity<Response> userDetails = usersController.deleteUserDetails(userStub, headers);
		
	    // verify
	    assertEquals(responseStub.getMessage(), userDetails.getBody().getMessage(), "User deleted successfull!!");
	 }
	 
	 private Users buildUser() {
		 Users user = new Users();
		 user.setDisplayName("P J");
		 user.setAge(43);
		 user.setAccountId("A0000000001");
		 user.setLocation("Pune, India");
		 user.setAboutMe("Sr. Technical Architect");
		 user.setProfileImageUrl("https://example.com/profile/A000000001.jpg");
		 user.setWebsiteUrl("https://example.com");
		 return user;
	 }
}
