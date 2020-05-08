
package ro.web.store.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(unique = true)
	@Size(min = 3, max = 30)
	private String productName;

	@Column()
	@Size(min = 3, max = 600)
	private String productDescription;

	@Column
	private long productPrice;

	@Column()
	private String productPhotoLink;

	@Column
	@Size(min = 3, max = 30)
	private String productCategory;

	@Column
	private long productStock;

	@Column
	private boolean deleted;


	@ElementCollection
	private List<String> imageList;


	public Product() {}

	public Product(String productName, String productDescription,
		long productPrice, String productPhotoLink, String productCategory,
		long productStock)
	{

		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productPhotoLink = productPhotoLink;
		this.productCategory = productCategory;
		this.productStock = productStock;
	}

}
