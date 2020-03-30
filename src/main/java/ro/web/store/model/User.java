
package ro.web.store.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Data
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUser;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String username;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String password;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String name;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String surname;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String email;

	@Column(unique = true)
	@Size(min = 3, max = 300)
	private String adress;

	@Column(unique = true)
	@Size(min = 10, max = 20)
	private long phoneNumber;

	public User() {}

	public User(long idUser, String username, String password, String name,
		String surname, String email, String adress, long phoneNumber)
	{
		this.idUser = idUser;
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
	}

}
