
package ro.web.store.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	Product mockProduct = new Product(4, "table11", "small coffe table", 1000,
		"https://imgur.com/gallery/eHKcitf", "Chairs");

	public Product addProduct(Product product) {
		return productRepository.save(this.mockProduct);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(this.mockProduct);
	}

	public void deleteProduct(Product product) {
		productRepository.deleteById(this.mockProduct.getId());
	}

	public List<Product> findAllProducts() throws EntityNotFoundException {
		List<Product> products = productRepository.findAll();
		if (products.isEmpty()) {
			throw new EntityNotFoundException(
				"Could not find any product in the database");
		}
		log.info("products test:", products);
		return products;
	}

	public List<Product> findByProductCategory(String productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}

}
