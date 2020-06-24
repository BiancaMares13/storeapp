
package ro.web.store.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Description;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ro.web.store.ConfigTest;
import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.model.Product;
import ro.web.store.repository.OrderRepository;
import ro.web.store.repository.ProductRepository;
import ro.web.store.repository.UserRepository;
import ro.web.store.service.ProductService;
import ro.web.store.utils.MockMvcUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest()
@DisplayName("Product Controller Test")
@Import(ConfigTest.class)
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
			"mese", 5, false);
				
		when(productService.addProduct(any())).thenReturn(product);
		
    mockMvc.perform(post("/products/addProduct")
			.content(MockMvcUtils.asJsonString(product))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isCreated())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.productName").value("masa"))		
	    .andExpect(jsonPath("$.productDescription").value("masa mica"))		
	    .andExpect(jsonPath("$.productPrice").value(10000))
      .andExpect(jsonPath("$.productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$.productCategory").value("mese"))
      .andExpect(jsonPath("$.productStock").value(5));		
	}
	
	@Test
	public void updateProductAPITest() throws Exception {
			
		Product product = new Product("masa", "masa mare", 10000, "link///photo",
			"mese", 5, false);
				
		when(productService.updateProduct(any())).thenReturn(product);
		
    mockMvc.perform(post("/products/updateProduct")
			.content(MockMvcUtils.asJsonString(product))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.productName").value("masa"))		
	    .andExpect(jsonPath("$.productDescription").value("masa mare"))		
	    .andExpect(jsonPath("$.productPrice").value(10000))
      .andExpect(jsonPath("$.productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$.productCategory").value("mese"))
      .andExpect(jsonPath("$.productStock").value(5));		
	} 
 
	@Test
	@Description("")
	public void deleteProductAPITest() throws Exception 
	{
		mockMvc.perform(post("/products/deleteProductById/{id}", 0) )
      .andExpect(status().isOk());
	}
	
	@Test

	public void findAllProductsAPITest() throws Exception 
	{
		Product product = new Product("masa", "masa mare", 10000, "link///photo",
			"mese", 5, false);
		Product product2 = new Product("masa de cafeaua", "masa mare", 50000, "link///photo",
			"mese", 5, false);
		
		Product product3 = new Product("masa", "masa mare", 10000, "link///photo",
			"mese", 5, false);
		
		List<Product> productList = new ArrayList<>();
		productList.add(product);	
		productList.add(product2);
		productList.add(product3);
		
		when(productService.findAllProducts()).thenReturn(productList);
		
    mockMvc.perform(get("/products/findAllProducts")
			.content(MockMvcUtils.asJsonString(productList))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").exists())
	    .andExpect(jsonPath("$[0].productName").value("masa"))		
      .andExpect(jsonPath("$[0].productDescription").value("masa mare"))		
      .andExpect(jsonPath("$[0].productPrice").value(10000))
      .andExpect(jsonPath("$[0].productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$[0].productCategory").value("mese"))
      .andExpect(jsonPath("$[0].productStock").value(5))
      .andExpect(jsonPath("$[1].id").exists())
      .andExpect(jsonPath("$[1].productName").value("masa de cafeaua"))		
      .andExpect(jsonPath("$[1].productDescription").value("masa mare"))		
      .andExpect(jsonPath("$[1].productPrice").value(50000))
      .andExpect(jsonPath("$[1].productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$[1].productCategory").value("mese"))
      .andExpect(jsonPath("$[1].productStock").value(5))
      .andExpect(jsonPath("$[2].id").exists())
      .andExpect(jsonPath("$[2].productName").value("masa"))		
      .andExpect(jsonPath("$[2].productDescription").value("masa mare"))		
      .andExpect(jsonPath("$[2].productPrice").value(10000))
      .andExpect(jsonPath("$[2].productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$[2].productCategory").value("mese"))
      .andExpect(jsonPath("$[2].productStock").value(5));			
	}
	
	@Test
	public void findAllProductsAPI_NegativTest() throws Exception {
				
		when(productService.findAllProducts()).thenThrow(EntityNotFoundException.class);
		
    mockMvc.perform(get("/products/findAllProducts")
			.accept(MediaType.APPLICATION_JSON))    
		  .andExpect(status().isNotFound());
	} 

	
	@Test
	public void findByProductCategoryAPITest() throws Exception {
			
		Product product = new Product("masa", "masa mare", 10000, "link///photo",
			"mese", 5, false);
		Product product2 = new Product("masa de cafeaua", "masa mare", 50000, "link///photo",
			"mese", 5, false);
		
		List<Product> productList = new ArrayList<>();
		productList.add(product);	
		productList.add(product2);
		
		when(productService.findByProductCategory("mese")).thenReturn(productList);
		
    mockMvc.perform(get("/products/findByProductCategory?productCategory=mese")
			.content(MockMvcUtils.asJsonString(productList))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isOk())
      .andExpect(jsonPath("$[0].id").exists())
	    .andExpect(jsonPath("$[0].productName").value("masa"))		
      .andExpect(jsonPath("$[0].productDescription").value("masa mare"))		
      .andExpect(jsonPath("$[0].productPrice").value(10000))
      .andExpect(jsonPath("$[0].productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$[0].productCategory").value("mese"))
      .andExpect(jsonPath("$[0].productStock").value(5))
      .andExpect(jsonPath("$[1].id").exists())
      .andExpect(jsonPath("$[1].productName").value("masa de cafeaua"))		
      .andExpect(jsonPath("$[1].productDescription").value("masa mare"))		
      .andExpect(jsonPath("$[1].productPrice").value(50000))
      .andExpect(jsonPath("$[1].productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$[1].productCategory").value("mese"))
      .andExpect(jsonPath("$[1].productStock").value(5));
	} 
	
	
	@Test
	public void findProductByIdAPITest() throws Exception 
	{
		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5, false);
				
		when(productService.findProductById(0)).thenReturn(product);
		
    mockMvc.perform(get("/products/{id}", 0)
			.content(MockMvcUtils.asJsonString(product))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isOk())
		  .andExpect(jsonPath("$.id").value("0"))		
		  .andExpect(jsonPath("$.productName").value("masa"))		
	    .andExpect(jsonPath("$.productDescription").value("masa mica"))		
	    .andExpect(jsonPath("$.productPrice").value(10000))
      .andExpect(jsonPath("$.productPhotoLink").value("link///photo"))		
      .andExpect(jsonPath("$.productCategory").value("mese"))
      .andExpect(jsonPath("$.productStock").value(5));	
	}
	
	@Test
	public void findProductByIdAPI_NegativTest() throws Exception {
				
		when(productService.findProductById(0)).thenThrow(EntityNotFoundException.class);
		
    mockMvc.perform(get("/products/{id}", 0)
			.accept(MediaType.APPLICATION_JSON))    
		  .andExpect(status().isNotFound());
	} 

	
	
	@Test
	public void findAllProductCategoriesAPITest() throws Exception {
					
		String category1 = "masa";
		String category2 = "scaun";
			
		Set<String> categoryList = new HashSet<>();
		categoryList.add(category1);	
		categoryList.add(category2);
		
		when(productService.findAllProductCategories()).thenReturn(categoryList);
		
    mockMvc.perform(get("/products/findAllCategories")
			.content(MockMvcUtils.asJsonString(categoryList))
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON))    
      .andDo(print())
		  .andExpect(status().isOk())
	    .andExpect(jsonPath("$[0]").value("masa"))
      .andExpect(jsonPath("$[1]").value("scaun"));	
	} 
	
	@Test
	public void findAllProductCategoriesAPI_NegativTest() throws Exception {
				
		when(productService.findAllProductCategories()).thenThrow(EntityNotFoundException.class);
		
    mockMvc.perform(get("/products/findAllCategories")
			.accept(MediaType.APPLICATION_JSON))    
		  .andExpect(status().isNotFound());
	} 


}
