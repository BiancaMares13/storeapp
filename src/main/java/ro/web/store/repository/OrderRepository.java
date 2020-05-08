
package ro.web.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ro.web.store.model.Order;
import ro.web.store.model.OrderStatus;
import ro.web.store.model.Product;
import ro.web.store.model.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByUser(User user);
	
	Order findOrderByUserIdAndStatus(long id, OrderStatus orderStatus);
}
