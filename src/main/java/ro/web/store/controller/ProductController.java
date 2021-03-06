
package ro.web.store.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.exception.UnableToModifyDataException;
import ro.web.store.model.Product;
import ro.web.store.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping("/addProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) throws InvalidInputDataException {
		Product newProduct = productService.addProduct(product);
		return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
	}

	@PostMapping("/updateProduct")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws InvalidInputDataException {
		Product newProduct = productService.updateProduct(product);
		return new ResponseEntity<>(newProduct, HttpStatus.OK);
	}

	@PostMapping("/deleteProductById/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable("id") long id) throws UnableToModifyDataException {
		Product newProduct =productService.deleteProductById(id);
		return new ResponseEntity<>(newProduct,HttpStatus.OK);
	}

	@GetMapping("/findAllProducts")
	public ResponseEntity<List<Product>> findAllProducts() throws EntityNotFoundException {
		List<Product> productList = productService.findAllProducts();
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping("/findByProductCategory")
	@ResponseBody
	public ResponseEntity<List<Product>> findByProductCategory(@RequestParam String productCategory) {
		List<Product> productList = productService.findByProductCategory(productCategory);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Product> findProductById(@PathVariable("id") long id) throws EntityNotFoundException {
		Product product = productService.findProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);
	}

	@GetMapping("/findAllCategories")
	public ResponseEntity<Set<String>> findAllCategories() throws EntityNotFoundException {

		Set<String> categoryList = productService.findAllProductCategories();
		return new ResponseEntity<>(categoryList, HttpStatus.OK);
	}

}
