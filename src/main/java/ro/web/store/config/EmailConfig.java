
package ro.web.store.config;

import java.util.Properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.Data;
import ro.web.store.utils.AppConstants;

@Data
@ConfigurationProperties(prefix = "mail")
public class EmailConfig {

	public String defaultEncoding;
	public String host;
	public String username;
	public String password;
	public int port;
	public String protocol;
	public boolean auth;
	public boolean starttlsEnable;
	public boolean testConnection;

	@Bean
	public JavaMailSender getJavaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setDefaultEncoding(getDefaultEncoding());
		mailSender.setHost(getHost());
		// username: testsurnametestname0@gmail.com
		mailSender.setUsername(getUsername());
		// password: qwerty123456&&&
		mailSender.setPassword(getPassword());
		mailSender.setPort(getPort());

		Properties props = mailSender.getJavaMailProperties();
		props.put(AppConstants.PROTOCOL_PROPERTY, protocol);
		props.put(AppConstants.MAIL_SMTP_AUTH_PROPERTY, auth);
		props.put(AppConstants.MAIL_SMTP_STARTTLS_ENABLE_PROPERTY, starttlsEnable);
		props.put(AppConstants.MAIL_DEBUG_PROPERTY, testConnection);

		return mailSender;
	}
}
