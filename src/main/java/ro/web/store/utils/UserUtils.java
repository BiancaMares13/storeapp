
package ro.web.store.utils;

import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Service;

@Service
public class UserUtils {

	public String encryptPassword(String password) {
		return Hashing.sha256().hashString(password, StandardCharsets.UTF_8)
			.toString();
	}

}
