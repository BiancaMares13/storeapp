package ro.web.store.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import ro.web.store.model.Order;
import ro.web.store.model.OrderStatus;
import ro.web.store.model.Product;
import ro.web.store.model.User;
import ro.web.store.model.UserRole;
import ro.web.store.repository.OrderRepository;
import ro.web.store.repository.ProductRepository;
import ro.web.store.repository.UserRepository;
import ro.web.store.service.OrderService;
import ro.web.store.utils.MockMvcUtils;


@WebMvcTest()
@DisplayName("Order Controller Test")
public class OrderControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	 OrderService orderService;

	@MockBean
	ProductRepository productRepository;

	@MockBean
	OrderRepository orderRepository;

	@MockBean
	UserRepository userRepository;
	
	
	
	public Order simpleOrderForTest() throws ParseException {
		
		User user = new User( "testUserName", "testPW", "name", "surname",
			"email@test.com", "adress Str.test, nr 6", "0700000000", UserRole.ROLE_USER);
				
		Product product = new Product("masa", "masa mare", 10000, "link///photo",
			"mese", 5);
		Product product2 = new Product("masa de cafeaua", "masa mare", 50000, "link///photo",
			"mese", 5);
		
		List<Product> productList = new ArrayList<>();
		productList.add(product);	
		productList.add(product2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		
		Order order = new Order("1", OrderStatus.DONE, user,
			productList, 10000.0, sdf.parse("2020-11-11 12:12:12"));
		
		return order;
	}
	
	@Test
	public void addOrderAPITest() throws Exception {
			
		when(orderService.addOrder(any())).thenReturn(simpleOrderForTest());
		
    mockMvc.perform(post("/orders/addOrder")
			.content(MockMvcUtils.asJsonString(simpleOrderForTest()))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isCreated())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.unic_identity_code").value("1"))		
	    .andExpect(jsonPath("$.status").value("DONE"))		
	    .andExpect(jsonPath("$.user.username").value("testUserName"))
      .andExpect(jsonPath("$.user.password").value("testPW"))
      .andExpect(jsonPath("$.productList[0].id").exists())
      .andExpect(jsonPath("$.productList[0].productName").value("masa"))		
      .andExpect(jsonPath("$.productList[0].productDescription").value("masa mare"))
      .andExpect(jsonPath("$.productList[1].id").exists())
      .andExpect(jsonPath("$.productList[1].productName").value("masa de cafeaua"))		
      .andExpect(jsonPath("$.productList[1].productDescription").value("masa mare"))
	    .andExpect(jsonPath("$.total").value(10000))	
	    .andExpect(jsonPath("$.completedOn").value("2020-11-11T12:12:12.000+0000"));	
	}
	
	@Test
	public void findOrderByUserIdAPITest() throws Exception {
		
		List<Order> orderList = new ArrayList<>();
    orderList.add(simpleOrderForTest());
		
  	when(orderService.findOrdersByUser(0)).thenReturn(orderList);

    mockMvc.perform(get("/orders/{id}", 0)
			.content(MockMvcUtils.asJsonString(orderList))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
  	  .andExpect(status().isOk())
		  .andExpect(jsonPath("$[0].id").value("0"))		
		  .andExpect(jsonPath("$[0].unic_identity_code").value("1"))		
	    .andExpect(jsonPath("$[0].status").value("DONE"))		
	    .andExpect(jsonPath("$[0].user.username").value("testUserName"))
      .andExpect(jsonPath("$[0].user.password").value("testPW"))
      .andExpect(jsonPath("$[0].productList[0].id").exists())
      .andExpect(jsonPath("$[0].productList[0].productName").value("masa"))		
      .andExpect(jsonPath("$[0].productList[0].productDescription").value("masa mare"))
      .andExpect(jsonPath("$[0].productList[1].id").exists())
      .andExpect(jsonPath("$[0].productList[1].productName").value("masa de cafeaua"))		
      .andExpect(jsonPath("$[0].productList[1].productDescription").value("masa mare"))
	    .andExpect(jsonPath("$[0].total").value(10000))	
	    .andExpect(jsonPath("$[0].completedOn").value("2020-11-11T12:12:12.000+0000"));
	} 
	
}
