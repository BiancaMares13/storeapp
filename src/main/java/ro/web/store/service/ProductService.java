
package ro.web.store.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.exception.EntityNotFoundException;
import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.Product;
import ro.web.store.repository.ProductRepository;
import ro.web.store.validator.ProductValidator;

@Service
public class ProductService {

	@Autowired
	private ProductValidator productValidator;

	@Autowired
	private ProductRepository productRepository;

	public Product addProduct(Product product) throws InvalidInputDataException {

		if (!productValidator.isProductnameUnique(product.getProductName())) {
			throw new InvalidInputDataException(
				"The Selected Product name is already used!");
		}

		if (!productValidator.isProductDataSizeCorrect(product.getProductName(), 3,
			30))
		{
			throw new InvalidInputDataException(
				"Product Name has to be between 3 and 30 characters long!");
		}

		if (!productValidator.isProductDataSizeCorrect(product
			.getProductDescription(), 3, 600))
		{
			throw new InvalidInputDataException(
				"Product Description has to be between 3 and 600 characters long!");
		}

		if (!productValidator.isProductDataSizeCorrect(product.getProductCategory(),
			3, 30))
		{
			throw new InvalidInputDataException(
				"Product Category has to be between 3 and 30 characters long!");
		}
		return productRepository.save(product);
	}

	public Product updateProduct(Product product)
		throws InvalidInputDataException
	{
		if (!productValidator.isSameProductname(product.getProductName())) {
			if (!productValidator.isProductnameUnique(product.getProductName())) {
				throw new InvalidInputDataException(
					"The Selected Product name is already used!");
			}
		}

		if (!productValidator.isProductDataSizeCorrect(product.getProductName(), 3,
			30))
		{
			throw new InvalidInputDataException(
				"Product Name has to be between 3 and 30 characters long!");
		}

		if (!productValidator.isProductDataSizeCorrect(product
			.getProductDescription(), 3, 600))
		{
			throw new InvalidInputDataException(
				"Product Description has to be between 3 and 600 characters long!");
		}

		if (!productValidator.isProductDataSizeCorrect(product.getProductCategory(),
			3, 30))
		{
			throw new InvalidInputDataException(
				"Product Category has to be between 3 and 30 characters long!");
		}
		return productRepository.save(product);
	}

	public void deleteProductById(long id) {
		productRepository.deleteById(id);
	}

	public List<Product> findAllProducts() throws EntityNotFoundException {
		List<Product> products = productRepository.findAll();
		products.stream().forEach(product -> product.setOrders(null)); //setting here to null so we can not have circular dependency
		if (products.isEmpty()) {
			throw new EntityNotFoundException(
				"Could not find any product in the database");
		}
		return products;
	}

	public List<Product> findByProductCategory(String productCategory) {
		return productRepository.findByProductCategory(productCategory);
	}

	public Product findProductById(long id) throws EntityNotFoundException {

		Optional<Product> optionalProduct = productRepository.findById(id);
		if (!optionalProduct.isPresent()) {
			throw new EntityNotFoundException(
				"Could not find any product in the database");
		}
		return optionalProduct.get();
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
