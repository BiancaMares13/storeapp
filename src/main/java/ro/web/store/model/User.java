
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

	@Column()
	@Size(min = 3, max = 30)
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
