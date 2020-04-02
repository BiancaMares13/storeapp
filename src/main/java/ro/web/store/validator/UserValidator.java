
package ro.web.store.validator;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import ro.web.store.repository.UserRepository;

@Service
@Slf4j
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
		if (userRepository.findByUsername(username) == null) return true;
		return false;
	}

	public boolean isPhoneNumberValid(String phoneNumber) {
		String phoneNumberRegex = "^(?=(?:[07]){2})(?=[0-9]{10}).*";

		Pattern pat = Pattern.compile(phoneNumberRegex);
		if (phoneNumber == null) return false;
		return pat.matcher(phoneNumber).matches();
	}

	public String encryptPassword(String password) {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8)
			.toString();
	
	}
	


}
