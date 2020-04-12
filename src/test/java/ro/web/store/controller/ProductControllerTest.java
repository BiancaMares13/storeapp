
package ro.web.store.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import ro.web.store.model.Product;
import ro.web.store.repository.OrderRepository;
import ro.web.store.repository.ProductRepository;
import ro.web.store.repository.UserRepository;
import ro.web.store.service.ProductService;
import ro.web.store.utils.MockMVCUtils;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	 ProductService productService;

	@MockBean
	ProductRepository productRepository;

	@MockBean
	OrderRepository orderRepository;

	@MockBean
	UserRepository userRepository;
	

	@Test
	public void addProductAPITest() throws Exception {
			
		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);
				
		when(productService.addProduct(any())).thenReturn(product);
		
    mockMvc.perform(post("/products/addProduct")
			.content(MockMVCUtils.asJsonString(product))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isCreated())
		  .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("0"))		
		  .andExpect(MockMvcResultMatchers.jsonPath("$.productName").value("masa"))		
	    .andExpect(MockMvcResultMatchers.jsonPath("$.productDescription").value("masa mica"))		
	    .andExpect(MockMvcResultMatchers.jsonPath("$.productPrice").value(10000))
      .andExpect(MockMvcResultMatchers.jsonPath("$.productPhotoLink").value("link///photo"))		
      .andExpect(MockMvcResultMatchers.jsonPath("$.productCategory").value("mese"))
      .andExpect(MockMvcResultMatchers.jsonPath("$.productStock").value(5));		
	}



}
