
package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	Product mockProduct = new Product(4, "table2", "small coffe table", 1000,
		"https://imgur.com/gallery/eHKcitf", "Tables");

	public Product addProduct(Product product) {
		return productRepository.save(this.mockProduct);
	}

	public Product updateProduct(Product product) {
		return productRepository.save(this.mockProduct);
	}

	public void deleteProduct(Product product) {
		productRepository.deleteById(this.mockProduct.getIdProduct());
	}
}
