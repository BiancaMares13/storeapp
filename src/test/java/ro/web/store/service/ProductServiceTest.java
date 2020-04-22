
package ro.web.store.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.qameta.allure.Description;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;
import ro.web.store.validator.ProductValidator;

@DisplayName("Product Service Test")
public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productRepository;

	@Mock
	ProductValidator productValidator;

	@BeforeEach
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Description("We create a simple mock Product and check if it was succesfully added into the database")
	@DisplayName("Test: A product was succesfully added into the Database")
	public void addProductTest() throws InvalidInputDataException {

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);

		when(productValidator.isProductnameUnique(ArgumentMatchers.anyString()))
			.thenReturn(true);

		when(productValidator.isProductDataSizeCorrect(ArgumentMatchers.anyString(),
			ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(true);

		productService.addProduct(product);

		verify(productRepository, times(1)).save(product);
	}

	@Test

	@Description("Test")
	public void updateProductTest() throws InvalidInputDataException {

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);
		
		when(productValidator.isProductnameUnique(ArgumentMatchers.anyString()))
		.thenReturn(true);

	 when(productValidator.isProductDataSizeCorrect(ArgumentMatchers.anyString(),
		ArgumentMatchers.anyInt(), ArgumentMatchers.anyInt())).thenReturn(true);

		productService.updateProduct(product);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void deleteProductTest() {

		productService.deleteProductById(1);
		verify(productRepository, times(1)).deleteById(1L);
	}

	@Test
	public void findAllProductsTest() throws EntityNotFoundException {

		List<Product> productList = new ArrayList<>();

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);
		Product product1 = new Product("masa1", "masa mica1", 10000, "link///photo",
			"mese", 5);

		productList.add(product);
		productList.add(product1);

		when(productRepository.findAll()).thenReturn(productList);

		// test
		List<Product> newProductList = productService.findAllProducts();

		assertEquals(2, newProductList.size());
		verify(productRepository, times(1)).findAll();
	}

	@Test
	public void findAllProducts_EntityNotFoundExceptionTest() {
		List<Product> productList = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(productList);

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			productService.findAllProducts();
		});

	}

	@Test
	public void findByProductCategoryTest() {
		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);

		productService.findByProductCategory(product.getProductCategory());

		verify(productRepository, times(1)).findByProductCategory(product
			.getProductCategory());
	}

	@Test
	public void findProductByIdTest() throws EntityNotFoundException {
		when(productRepository.findById(0L)).thenReturn(Optional.of(new Product(
			"masa", "masa mica", 10000, "link///photo", "mese", 5)));

		Product product = productService.findProductById(0L);

		assertEquals(0, product.getId());
		assertEquals("masa", product.getProductName());
		assertEquals("masa mica", product.getProductDescription());
		assertEquals(10000, product.getProductPrice());
		assertEquals("link///photo", product.getProductPhotoLink());
		assertEquals("mese", product.getProductCategory());
		assertEquals(5, product.getProductStock());

		verify(productRepository, times(1)).findById(product.getId());
	}

	@Test
	public void findProductById_NegativTest() {
		when(productRepository.findById(0L)).thenReturn(Optional.ofNullable(null));

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			productService.findProductById(0L);
		});

	}

	@Test
	public void findAllProductCategoriesTest() throws EntityNotFoundException {

		List<Product> productList = new ArrayList<>();

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);
		Product product1 = new Product("masa de batut jnitele", "masa mare", 10000,
			"link///photo", "mese", 5);
		Product product2 = new Product("masa de portelan", "masa mare", 10000,
			"link///photo", "mese", 5);
		Product product3 = new Product("masa de fierar", "masa mare", 10000,
			"link///photo", "mese", 5);

		productList.add(product);
		productList.add(product1);
		productList.add(product2);
		productList.add(product3);

		when(productRepository.findAll()).thenReturn(productList);
		Set<String> categories = productService.findAllProductCategories();

		assertEquals(1, categories.size());
		verify(productRepository, times(1)).findAll();

	}

	@Test
	public void findAllProductCategories_EntityNotFoundExceptionTest() {
		List<Product> productList = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(productList);

		Assertions.assertThrows(EntityNotFoundException.class, () -> {
			productService.findAllProductCategories();
		});

	}
}
