
package ro.web.store.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

public class ProductServiceTest {

	@InjectMocks
	ProductService productService;

	@Mock
	ProductRepository productRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void addProductTest() {

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);

		productService.addProduct(product);

		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void updateProductTest() {

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);

		productService.updateProduct(product);
		verify(productRepository, times(1)).save(product);
	}

	@Test
	public void deleteProductTest() {

		Product product = new Product("masa", "masa mica", 10000, "link///photo",
			"mese", 5);

		productService.deleteProduct(product);
		verify(productRepository, times(1)).deleteById(product.getId());
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

	@Test(expected = EntityNotFoundException.class)
	public void findAllProducts_EntityNotFoundExceptionTest()
		throws EntityNotFoundException
	{
		List<Product> productList = new ArrayList<>();
		when(productRepository.findAll()).thenReturn(productList);
		// test
		productService.findAllProducts();
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

	@Test(expected = EntityNotFoundException.class)
	public void findAllProductCategories_EntityNotFoundExceptionTest()
		throws EntityNotFoundException
	{
		List<Product> productList = new ArrayList<>();

		when(productRepository.findAll()).thenReturn(productList);
		productService.findAllProductCategories();

	}
}
