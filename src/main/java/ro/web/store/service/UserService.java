
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

	User mockUser = new User(1, "testUserName1111", "qwerty123##ASD", "TestName",
		"TestSurname", "testsurnametestname0@gmail.com", "Str.Test, nr 0, Ap 0",
		0700700700);

	public User addUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(user.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		return userRepository.save(mockUser);
	}

	public User updateUser(User user) throws InvalidInputDataException {
		if (!userValidator.isEmailValid(user.getEmail())) {
			throw new InvalidInputDataException("invalid email format!");
		}
		return userRepository.save(this.mockUser);
	}

	public void deleteUser(User user) {
		userRepository.deleteById(this.mockUser.getIdUser());
	}
}
