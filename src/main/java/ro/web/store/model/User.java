
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

import io.swagger.annotations.ApiModelProperty;

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
	@ApiModelProperty(example = "johndoe <--Unique")
	private String username;

	@Column
	@ApiModelProperty(example = "11111")
	private String password;

	@Column()
	@Size(min = 3, max = 30)
	@ApiModelProperty(example = "Doe")
	private String name;

	@Column()
	@Size(min = 3, max = 30)
	@ApiModelProperty(example = "John")
	private String surname;

	@Column()
	@Size(min = 3, max = 30)
	@ApiModelProperty(example = "doe.john@mail.com")
	private String email;

	@Column()
	@Size(min = 3, max = 300)
	@ApiModelProperty(example = "stret, no. 1")
	private String adress;

	@Column
	@Size(min = 10, max = 15)
	@ApiModelProperty(example = "+40720000000")
	private String phoneNumber;

	@Column
	@ApiModelProperty(example = "ROLE_USER/ ROLE_ADMIN")
	private UserRole userRole;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {
		CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	@JsonIgnoreProperties("users")
	@ApiModelProperty(example = "[]")
	private List<Order> order;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
		CascadeType.REFRESH })
	@JoinTable(name = "favorite_product_list", joinColumns = @JoinColumn(
		name = "user_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	@ApiModelProperty(example = "[]")
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
