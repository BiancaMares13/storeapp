
package ro.web.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.web.store.model.User;
import ro.web.store.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User addUser(User u) {
		u = new User("tes2t");
		return userRepository.save(u);
	}
}
