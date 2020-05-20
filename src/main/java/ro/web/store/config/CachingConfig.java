
package ro.web.store.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import ro.web.store.StoreApplication;

@SpringBootApplication
@EnableCaching
public class CachingConfig {

	public static void main(String[] args) {
		SpringApplication.run(StoreApplication.class, args);
	}
}
