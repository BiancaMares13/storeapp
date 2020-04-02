
package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.exception.InvalidInputDataException;
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

	public void deleteUser(User user) {
		userRepository.deleteById(user.getIdUser());
	}

	public User findByUsername(String username) {
		User checkedUser = userRepository.findByUsername(username);
		return checkedUser;
	}

}
