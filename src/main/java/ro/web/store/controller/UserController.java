
package ro.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.web.store.exception.DuplicateEntryException;
import ro.web.store.exception.InvalidInputDataException;
import ro.web.store.model.Product;
import ro.web.store.model.User;
import ro.web.store.model.UserCredentials;
import ro.web.store.model.UserRole;
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
	public ResponseEntity<User> addUser(@RequestBody User u)
		throws InvalidInputDataException
	{
		u.setUserRole(UserRole.ROLE_USER);
		User newUser = userService.addUser(u);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}

	@PostMapping("/updateUser")
	public ResponseEntity<User> updateUser(@RequestBody User user)
		throws InvalidInputDataException
	{
		User newUser = userService.updateUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Product> deleteProductById(
		@PathVariable("id") long id)
	{
		userService.deleteUserById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/findByUserId")
	@ResponseBody
	public ResponseEntity<User> findByUserId(@RequestParam long id) {
		User user = userService.findByUserId(id);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<User> login(@RequestBody UserCredentials credentials) {
		User newUser = userService.findByUsername(credentials.getUsername());

		if (newUser.getPassword().contentEquals(credentials.getPassword())) {
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
	}

	@PostMapping("/addFavorite/{id}")
	@ResponseBody
	public ResponseEntity<User> addProductToFavorites(@PathVariable("id") long id,
		@RequestBody Product product) throws  DuplicateEntryException
	{
		User user = userService.addProductToFavorites(id, product);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@PostMapping("/removeFavorite/{id}")
	@ResponseBody
	public ResponseEntity<User> removeProductFromFavorites(
		@PathVariable("id") long id, @RequestBody Product product)
	{
		User user = userService.removeProductFromFavorites(id, product);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

	@GetMapping("/getAllFavoritesProduts")
	@ResponseBody
	public ResponseEntity<List<Product>> getAllFavoritesProduts(@RequestParam long id) {
		List<Product> products = userService.getAllFavoriteProducts(id);
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
}
