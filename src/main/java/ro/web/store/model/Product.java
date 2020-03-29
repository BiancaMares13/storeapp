
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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long idProduct;

	@Column(unique = true)
	@Size(min = 3, max = 15)
	private String productName;

	@Column()
	@Size(min = 3, max = 600)
	private String productDescription;

	@Column
	private long productPrice;

	@Column()
	private String productPhotoLink;

	@Column
	@Size(min = 3, max = 15)
	private String productCategory;

	public Product() {}

	public Product(long idProduct, String productName, String productDescription,
		long productPrice, String productPhotoLink, String productCategory)
	{
		this.idProduct = idProduct;
		this.productName = productName;
		this.productDescription = productDescription;
		this.productPrice = productPrice;
		this.productPhotoLink = productPhotoLink;
		this.productCategory = productCategory;
	}

}
