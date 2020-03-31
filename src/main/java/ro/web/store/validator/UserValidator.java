
package ro.web.store.validator;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.repository.UserRepository;

@Service
public class UserValidator {

	@Autowired
	private UserRepository userRepository;

	public boolean isEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +
			"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) return false;
		return pat.matcher(email).matches();
	}

	public boolean isUsernameValid(String username) {
		String usernameRegex = "[A-Za-z0-9_]+";

		Pattern pat = Pattern.compile(usernameRegex);
		if (username == null) return false;
		return pat.matcher(username).matches();
	}

	public boolean isUsernameUnique(String username) {

		if (userRepository.findByUsername(username) == null) 
			return true;
		return false;
	}

}