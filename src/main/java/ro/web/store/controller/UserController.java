
package ro.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.User;
import ro.web.store.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * Endpoint mapeaza un request HTTP la metoda addUser
	 * 
	 * @param u
	 * @throws InvalidInputDataException
	 */
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(User u) throws InvalidInputDataException {
		User newUser = userService.addUser(u);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(User user)
		throws InvalidInputDataException
	{
		User newUser = userService.updateUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<User> deleteUser(User user) {
		userService.deleteUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<User> login(@RequestParam String username,
		@RequestParam String password)
	{
		User newUser = userService.findByUsername(username);

		if (newUser.getPassword().contentEquals(password)) {
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

}
