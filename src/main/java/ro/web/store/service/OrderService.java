
package ro.web.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Order;
import ro.web.store.model.Product;
import ro.web.store.model.User;
import ro.web.store.repository.OrderRepository;
import ro.web.store.repository.ProductRepository;
import ro.web.store.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ProductRepository productRepository;

	public Order addOrder(Order order) {

		List<Product> productList = order.getProductList();

		for (Product product : productList) {
			Product productById = productRepository.findById(product.getId()).get();

			productById.setProductStock(productById.getProductStock() - 1);
			productRepository.save(productById);
		}

		return orderRepository.save(order);
	}

	public List<Order> findOrdersByUser(long id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) return orderRepository.findByUser(user.get());
		return null;
	}
}
