
package ro.web.store.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Order;
import ro.web.store.model.User;
import ro.web.store.repository.OrderRepository;
import ro.web.store.repository.UserRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private UserRepository userRepository;

	public List<Order> findOrderByUser(long id) {

		Optional<User> user = userRepository.findById(id);
		if (user.isPresent()) return orderRepository.findByUser(user.get());
		return null;
	}
}
