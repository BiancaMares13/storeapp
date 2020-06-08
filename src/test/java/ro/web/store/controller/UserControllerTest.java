package ro.web.store.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.web.store.ConfigTest;
import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.User;
import ro.web.store.model.UserCredentials;
import ro.web.store.model.UserRole;
import ro.web.store.repository.OrderRepository;
import ro.web.store.repository.ProductRepository;
import ro.web.store.repository.UserRepository;
import ro.web.store.service.UserService;
import ro.web.store.utils.MockMvcUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest()
@DisplayName("User Controller Test")
@Import(ConfigTest.class)
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	 UserService userService;

	@MockBean
	ProductRepository productRepository;

	@MockBean
	OrderRepository orderRepository;

	@MockBean
	UserRepository userRepository;
	
	@MockBean
	UserCredentials userCredentials;
	
	@Test
	public void addUserAPITest() throws Exception {
			
		User user = new User( "username", "password", "name", "surname",
			"email@test.com", "adress Str.test, nr 6", "0700000000",UserRole.ROLE_USER);
				
		when(userService.addUser(any())).thenReturn(user);
		
    mockMvc.perform(post("/users/addUser")
			.content(MockMvcUtils.asJsonString(user))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isCreated())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.username").value("username"))		
	    .andExpect(jsonPath("$.password").value("password"))		
	    .andExpect(jsonPath("$.surname").value("surname"))
      .andExpect(jsonPath("$.email").value("email@test.com"))		
      .andExpect(jsonPath("$.adress").value("adress Str.test, nr 6"))
      .andExpect(jsonPath("$.phoneNumber").value("0700000000"))	
      .andExpect(jsonPath("$.userRole").value("ROLE_USER"));		
	}
	
	@Test
	public void addUserAPI_NegativTest() throws Exception {
				
		when(userService.addUser(any())).thenThrow(InvalidInputDataException.class);
		
    mockMvc.perform(post("/users/addUser")
			.accept(MediaType.APPLICATION_JSON))    
		  .andExpect(status().isBadRequest());
	} 
	
	@Test
	public void updateUserAPITest() throws Exception {
			
		User user = new User( "username", "password", "name", "surname1",
			"email@test.com", "adress Str.test, nr 6", "0700000000",UserRole.ROLE_USER);
				
		when(userService.updateUser(any())).thenReturn(user);
		
    mockMvc.perform(post("/users/updateUser")
			.content(MockMvcUtils.asJsonString(user))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.username").value("username"))		
	    .andExpect(jsonPath("$.password").value("password"))		
	    .andExpect(jsonPath("$.surname").value("surname1"))
      .andExpect(jsonPath("$.email").value("email@test.com"))		
      .andExpect(jsonPath("$.adress").value("adress Str.test, nr 6"))
      .andExpect(jsonPath("$.phoneNumber").value("0700000000"))	
      .andExpect(jsonPath("$.userRole").value("ROLE_USER"));		
	}
	
	@Test
	public void updateUserAPI_NegativTest() throws Exception {
				
		when(userService.addUser(any())).thenThrow(InvalidInputDataException.class);
		
    mockMvc.perform(post("/users/updateUser")
			.accept(MediaType.APPLICATION_JSON))    
		  .andExpect(status().isBadRequest());
	} 
	
	@Test
	public void deleteUserAPITest() throws Exception 
	{
		mockMvc.perform(delete("/users/{id}", 0) )
      .andExpect(status().isOk());
	}
	
	@Test
	public void findByUserIdAPITest() throws Exception {
			
		User user = new User( "username", "password", "name", "surname1",
			"email@test.com", "adress Str.test, nr 6", "0700000000",UserRole.ROLE_USER);
				
		when(userService.findByUserId(0)).thenReturn(user);
		
    mockMvc.perform(get("/users/findByUserId?id=0")
			.content(MockMvcUtils.asJsonString(user))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
  	  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.username").value("username"))		
	    .andExpect(jsonPath("$.password").value("password"))		
	    .andExpect(jsonPath("$.surname").value("surname1"))
      .andExpect(jsonPath("$.email").value("email@test.com"))		
      .andExpect(jsonPath("$.adress").value("adress Str.test, nr 6"))
      .andExpect(jsonPath("$.phoneNumber").value("0700000000"))	
      .andExpect(jsonPath("$.userRole").value("ROLE_USER"));	

	} 
		
	@Test
	public void loginAPITest() throws Exception {
			
		User user = new User( "username", "ca8d3bbf2f0a9ce3e3fa369f7bd9936be9ddb7662f800f381bd3172d42006032", "name", "surname",
			"email@test.com", "adress Str.test, nr 6", "0700000000",UserRole.ROLE_USER);
		
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername("username");
		credentials.setPassword("ca8d3bbf2f0a9ce3e3fa369f7bd9936be9ddb7662f800f381bd3172d42006032");
		
		when(userService.findByUsername("username")).thenReturn(user);
		
    mockMvc.perform(post("/users/login")
			.content(MockMvcUtils.asJsonString(credentials))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print()) 
      .andExpect(status().isOk())	
  		.andExpect(jsonPath("$.username").value("username"))		
  	  .andExpect(jsonPath("$.password").value("ca8d3bbf2f0a9ce3e3fa369f7bd9936be9ddb7662f800f381bd3172d42006032"));		
	}
	
	@Test
	public void loginAPI_NegativTest() throws Exception {
			
		User user = new User( "username", "ca8d3bbf2f0a9ce3e3fa369f7bd9936be9ddb7662f800f381bd3172d42006032", "name", "surname",
			"email@test.com", "adress Str.test, nr 6", "0700000000",UserRole.ROLE_USER);
		
		UserCredentials credentials = new UserCredentials();
		credentials.setUsername("username");
		credentials.setPassword("wrongPassword");
		
		when(userService.findByUsername("username")).thenReturn(user);
		
    mockMvc.perform(post("/users/login")
			.content(MockMvcUtils.asJsonString(credentials))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andExpect(status().isUnauthorized());		
	}

}
