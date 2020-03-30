
package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ro.web.store.model.User;
import ro.web.store.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	User mockUser = new User(4,"testUserName", "qwerty123##ASD", "TestName", "TestSurname",
		"testsurnametestname0@gmail.com", "Str.Test, nr 0, Ap 0", 0700700700);
	
	public User addUser(User user) {
		return userRepository.save(mockUser);
	}
}
