package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) {
		product = new Product("table", "small coffe table", 1000, "https://imgur.com/gallery/eHKcitf", "Tables");
		return productRepository.save(product);
	}
}
