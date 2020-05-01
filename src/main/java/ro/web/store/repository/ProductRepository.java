
package ro.web.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.web.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByProductCategoryAndDeleted(String productCategory,boolean isDeleted);

	List<Product> findByDeleted(boolean isDeleted);

	Product findByProductName(String productname);
}
