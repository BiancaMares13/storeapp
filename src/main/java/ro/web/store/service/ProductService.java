
package ro.web.store.service;

import com.sun.xml.bind.v2.model.core.ID;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;

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

	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	public List<Product> findByProductCategory(String productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}
}
