
package ro.web.store.service;

import java.util.List;

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

}
