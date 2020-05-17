
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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "users")
@EqualsAndHashCode(exclude = { "order", "favoriteProductList" })
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

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {
		CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JsonIgnoreProperties("users")
	private List<Order> order;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
		CascadeType.REFRESH })
	@JoinTable(name = "favorite_product_list", joinColumns = @JoinColumn(
		name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private List<Product> favoriteProductList;

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
