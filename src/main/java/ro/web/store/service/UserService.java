
package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.User;
import ro.web.store.repository.UserRepository;
import ro.web.store.validator.UserValidator;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserValidator userValidator;

	User mockUser = new User(1, "testUser999", "qwerty123##ASD", "TestName",
		"TestSurname", "testsurnametestname0@gmail.com", "Str.Test, nr 0, Ap 0",
		"0700700700");

	public User addUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(mockUser.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		if (!userValidator.isUsernameValid(mockUser.getUsername())) {
			throw new InvalidInputDataException("invalid username format!");
		}
//		if (!userValidator.isUsernameUnique(mockUser.getUsername())) {
//			throw new InvalidInputDataException("usename is already used!");
//		}
		if (!userValidator.isPhoneNumberValid(mockUser.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}

		mockUser.setPassword(userValidator.encryptPassword(mockUser.getPassword()));
		
		return userRepository.save(mockUser);
	}

	public User updateUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(mockUser.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		if (!userValidator.isUsernameValid(mockUser.getUsername())) {
			throw new InvalidInputDataException("invalid username format!");
		}
		if (!userValidator.isUsernameUnique(mockUser.getUsername())) {
			throw new InvalidInputDataException("usename is already used!");
		}
		if (!userValidator.isPhoneNumberValid(mockUser.getPhoneNumber())) {
			throw new InvalidInputDataException("invalid phone number format!");
		}

		return userRepository.save(this.mockUser);
	}

	public void deleteUser(User user) {
		userRepository.deleteById(this.mockUser.getIdUser());
	}
	
	public User findByUsername(String username) {
		
		User checkedUser = userRepository.findByUsername(username);
		return checkedUser;
	}
	

}
