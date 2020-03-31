
package ro.web.store.validator;

import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

@Service
public class UserValidator {

	public boolean isEmailValid(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" +
			"(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null) return false;
		return pat.matcher(email).matches();
	}
}
