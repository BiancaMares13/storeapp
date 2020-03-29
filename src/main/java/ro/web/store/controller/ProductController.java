package ro.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.web.store.model.Product;

import ro.web.store.service.ProductService;


@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(Product product) {
		Product newProduct = productService.addProduct(product);
		return new ResponseEntity<Product>(newProduct, HttpStatus.OK);
	}
}
