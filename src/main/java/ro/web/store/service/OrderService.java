
package ro.web.store.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.web.store.model.Order;
import ro.web.store.model.OrderStatus;
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

		Set<Product> productList = order.getProductList();

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

	public Order updateOrderStatus(long id, OrderStatus orderStatus) {

		Optional<Order> order = orderRepository.findById(id);
		if (!orderStatus.equals(order.get().getStatus())) {
			order.get().setStatus(orderStatus);
		}

		return orderRepository.save(order.get());
	}

	public List<Order> geAllOrdersByUserId(long id) {

		Optional<User> user = userRepository.findById(id);

		return orderRepository.findByUser(user.get());
	}
	

	public Order addProductToOrder(long id,Product product) {		
		Order order = orderRepository.findOrderByUserIdAndStatus(id, OrderStatus.IN_CART);
		User user=userRepository.findById(id).get();

		if(order !=null) {
			Set<Product> productList= order.getProductList();	
		    	productList.add(product);
		    	order.setProductList(productList);
			return orderRepository.save(order);
			
			}
		
			Order newOrder = new Order();
			newOrder.setStatus(OrderStatus.IN_CART);
			newOrder.setUser(user);
			Set<Product> newProductList = new HashSet<>();
			newProductList.add(product);
			newOrder.setProductList(newProductList);
			return orderRepository.save(newOrder);   	
	}
}
