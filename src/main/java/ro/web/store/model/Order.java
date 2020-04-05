
package ro.web.store.model;

import java.time.LocalDateTime;
import java.util.List;

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

import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	private String unic_identity_code;

	@Column()
	private OrderStatus status;


	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToMany
	@JoinTable(name = "list_of_products", joinColumns = @JoinColumn(
		name = "order_id"), inverseJoinColumns = @JoinColumn(
			name = "product_id"))
	private List<Product> productList;

	@Column
	private double total;

	@Column
	private LocalDateTime completedOn;

	public Order() {}

	public Order(String unic_identity_code, OrderStatus status, User user,
		List<Product> productList, double total, LocalDateTime completedOn)
	{

		this.unic_identity_code = unic_identity_code;
		this.status = status;
		this.user = user;
		this.productList = productList;
		this.total = total;
		this.completedOn = completedOn;
	}
}
