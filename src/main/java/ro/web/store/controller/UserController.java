
package ro.web.store.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 */
	@PostMapping("/addUser")
	public ResponseEntity<User> addUser(User u) {
		User newUser = userService.addUser(u);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@PostMapping("/updateUser")
	public ResponseEntity<User> updateProduct(User product) {
		User newProduct = userService.updateUser(product);
		return new ResponseEntity<>(newProduct, HttpStatus.OK);
	}

	@DeleteMapping("/deleteUser")
	public ResponseEntity<User> deleteUser(User product) {
		userService.deleteUser(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
