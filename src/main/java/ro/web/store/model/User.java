
package ro.web.store.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

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

	@Column
	private UserRole userRole;
	
  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,
      cascade = CascadeType.ALL)
  private List<Order> order;

	public User() {}

	public User(String username, String password, String name, String surname,
		String email, String adress, String phoneNumber, UserRole userRole)
	{
		this.username = username;
		this.password = password;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.userRole = userRole;
	}

}
