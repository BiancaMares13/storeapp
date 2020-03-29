package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	Product product = new Product(3,"table", "small coffe table", 1000, "https://imgur.com/gallery/eHKcitf", "Tables");

	
	public Product addProduct(Product product) {
		return productRepository.save(this.product);
	}
	
	public void deleteProduct(Product product) {
		productRepository.deleteById(this.product.getIdProduct());
	}
}
