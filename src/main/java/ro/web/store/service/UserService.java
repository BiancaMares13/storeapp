
package ro.web.store.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.Product;
import ro.web.store.model.User;
import ro.web.store.repository.UserRepository;
import ro.web.store.utils.UserUtils;
import ro.web.store.validator.UserValidator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserUtils userUtils;

	public User addUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(user.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		if (!userValidator.isUsernameValid(user.getUsername())) {
			throw new InvalidInputDataException("invalid username format!");
		}
		if (!userValidator.isUsernameUnique(user.getUsername())) {
			throw new InvalidInputDataException("usename is already used!");
		}
		if (!userValidator.isPhoneNumberValid(user.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}

		user.setPassword(userUtils.encryptPassword(user.getPassword()));

		return userRepository.save(user);
	}

	public User findByUserId(long id) {
		Optional<User> user = userRepository.findById(id);
		return user.get();
	}

	public User updateUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(user.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		if (!userValidator.isUsernameValid(user.getUsername())) {
			throw new InvalidInputDataException("invalid username format!");
		}
		if (!userValidator.isUsernameUnique(user.getUsername())) {
			throw new InvalidInputDataException("usename is already used!");
		}
		if (!userValidator.isPhoneNumberValid(user.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}

		return userRepository.save(user);
	}

	public void deleteUserById(long id) {
		userRepository.deleteById(id);
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User addProductToFavorites(long id, Product product) {
		User user = findByUserId(id);
		List<Product> updatedFavoriteProductList = new ArrayList<>();

		if (!user.getFavoriteProductList().equals(null)) {
			updatedFavoriteProductList = user.getFavoriteProductList();
		}
		updatedFavoriteProductList.add(product);
		user.setFavoriteProductList(updatedFavoriteProductList);

		return userRepository.save(user);
	}

	public User removeProductFromFavorites(long id, Product product) {
		User user = findByUserId(id);
		List<Product> updatedFavoriteProductList = new ArrayList<>();

		if (!user.getFavoriteProductList().equals(null)) {
			updatedFavoriteProductList = user.getFavoriteProductList();
			updatedFavoriteProductList.remove(product);
			user.setFavoriteProductList(updatedFavoriteProductList);
		}
		return userRepository.save(user);
	}

	public List<Product> getAllFavoriteProducts(long id) {
		User user = findByUserId(id);
		return user.getFavoriteProductList();
	}
	
}
