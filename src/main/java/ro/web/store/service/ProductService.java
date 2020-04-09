
package ro.web.store.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProduct(Product product) {
		productRepository.deleteById(product.getId());
	}

	public List<Product> findAllProducts() throws EntityNotFoundException {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new EntityNotFoundException(
				"Could not find any product in the database");
		}
		return products;
	}

	public List<Product> findByProductCategory(String productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}

	public Product findProductById(long id) {
		
		Optional<Product> optionalProduct = productRepository.findById(id);
		Product product = optionalProduct.get();
		return product;
	}

	public Set<String> findAllProductCategories() throws EntityNotFoundException {
		List<Product> products = productRepository.findAll();

		Set<String> categories = new HashSet<>();
		for (Product product : products) {
			categories.add(product.getProductCategory());
		}
		if (categories.isEmpty()) {
			throw new EntityNotFoundException(
				"Could not find any product categories in the database");
		}
		return categories;
	}

}
