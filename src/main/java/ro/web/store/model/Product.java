
package ro.web.store.model;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	@ApiModelProperty(example = "Table")
	private String productName;

	@Column()
	@Size(min = 3, max = 600)
	@ApiModelProperty(example = "grey Table")
	private String productDescription;

	@Column
	@ApiModelProperty(example = "1000")
	private long productPrice;

	@Column()
	@ApiModelProperty(example = "https://as1.ftcdn.net/8i7JVIa8cABN9.jpg")
	private String productPhotoLink;

	@Column
	@Size(min = 3, max = 30)
	@ApiModelProperty(example = "Tables")
	private String productCategory;

	@Column
	@ApiModelProperty(example = "1")
	private long productStock;

	@Column
	@ApiModelProperty(example = "false")
	private boolean deleted;

	@ElementCollection
	@ApiModelProperty(example = "[]")
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
