
package ro.web.store.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idUser;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String username;

	@Column
	private String password;

	@Column()
	@Size(min = 3, max = 30)
	private String name;

	@Column()
	@Size(min = 3, max = 30)
	private String surname;

	@Column()
	@Size(min = 3, max = 30)
	private String email;

	@Column()
	@Size(min = 3, max = 300)
	private String adress;

	@Column
	@Size(min = 10, max = 15)
	private String phoneNumber;

	public User() {}

	public User(long idUser, String username, String password, String name,
		String surname, String email, String adress, String phoneNumber)
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
