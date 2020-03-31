
package ro.web.store.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.web.store.exception.EntityNotFoundException;
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
		return new ResponseEntity<>(newProduct, HttpStatus.OK);
	}

	@PostMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(Product product) {
		Product newProduct = productService.updateProduct(product);
		return new ResponseEntity<>(newProduct, HttpStatus.OK);
	}

	@DeleteMapping("/deleteProduct")
	public ResponseEntity<Product> deleteProduct(Product product) {
		productService.deleteProduct(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/findAllProducts")
	public ResponseEntity<List<Product>> findAllProducts() throws EntityNotFoundException {
		List<Product> productList = productService.findAllProducts();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
	
	@GetMapping("/findByProductCategory")
	@ResponseBody
	public ResponseEntity<List<Product>> findByProductCategory(@RequestParam String productCategory)
	{
		List<Product> productList = productService.findByProductCategory(productCategory);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
}
