
package ro.web.store.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.repository.ProductRepository;

@Service
public class ProductValidator {

	@Autowired
	private ProductRepository productRepository;

	public boolean isProductnameUnique(String productname) {
		if (productRepository.findByProductName(productname) == null) return true;
		return false;
	}

	public boolean isSameProductname(String productname) {

		if (productRepository.findByProductName(productname).getProductName()
			.equals(productname)) return true;
		return false;
	}

	public boolean isProductDataSizeCorrect(String userData, int min, int max) {
		if (userData.length() < min || userData.length() > max) return false;
		return true;
	}
}
