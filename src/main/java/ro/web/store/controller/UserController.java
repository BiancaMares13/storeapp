
package ro.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.User;
import ro.web.store.model.UserCredentials;
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
	public ResponseEntity<User> addUser(@RequestBody User u) throws InvalidInputDataException {
		User newUser = userService.addUser(u);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user)
		throws InvalidInputDataException
	{
		User newUser = userService.updateUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<User> deleteUser(@RequestBody User user) {
		userService.deleteUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody UserCredentials credentials)
	{
		User newUser = userService.findByUsername(credentials.getUsername());

		if (newUser.getPassword().contentEquals(credentials.getPassword())) {
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

}
