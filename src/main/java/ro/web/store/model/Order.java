
package ro.web.store.model;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.EqualsAndHashCode;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
@EqualsAndHashCode(exclude = { "user", "productList" })
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String unic_identity_code;

	@Column()
	private OrderStatus status;

	@ManyToOne(fetch = FetchType.EAGER, optional = false,
		  cascade = {
        CascadeType.PERSIST, 
        CascadeType.MERGE,
        CascadeType.REFRESH 
    })
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnoreProperties("order")
	private User user;

	@ManyToMany
	@JoinTable(name = "list_of_products", joinColumns = @JoinColumn(
		name = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id"))
	private Set<Product> productList;

	@Column
	private double total;

	@Column
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(style = "yyyy-MM-dd HH:mm:ss")
	private Date completedOn;

	public Order() {}

	public Order(String unic_identity_code, OrderStatus status, User user,
		Set<Product> productList, double total, Date completedOn)
	{

		this.unic_identity_code = unic_identity_code;
		this.status = status;
		this.user = user;
		this.productList = productList;
		this.total = total;
		this.completedOn = completedOn;
	}
}
