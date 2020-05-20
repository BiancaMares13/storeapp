
package ro.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.web.store.exception.DuplicateEntryException;
import ro.web.store.model.Order;
import ro.web.store.model.OrderStatus;
import ro.web.store.model.Product;
import ro.web.store.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/addOrder")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {
		Order newOrder = orderService.addOrder(order);
		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<List<Order>> findOrdersByUserId(
		@PathVariable("id") long id)
	{
		List<Order> orders = orderService.findOrdersByUser(id);

		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@PostMapping(path = "/updateOrderStatus/{id}")
	@ResponseBody
	public ResponseEntity<Order> updateOrderStatus(@PathVariable("id") long id,
		@RequestBody OrderStatus orderStatus)
	{
		Order order = orderService.updateOrderStatus(id, orderStatus);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PostMapping(path = "/geAllOrdersByUserId/{id}")
	public ResponseEntity<List<Order>> geAllOrdersByUserId(
		@PathVariable("id") long id)
	{
		List<Order> order = orderService.geAllOrdersByUserId(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@PostMapping("/addProductToOrder/{id}")
	@ResponseBody
	public ResponseEntity<?> addProductToOrder(@PathVariable("id") long id,
		@RequestBody Product product) throws DuplicateEntryException
	{
		orderService.addProductToOrder(id, product);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@PostMapping("/removeProductfromOrder/{id}")
	@ResponseBody
	public ResponseEntity<?> removeProductfromOrder(@PathVariable("id") long id,
		@RequestBody Product product) throws DuplicateEntryException
	{
		orderService.removeProductfromOrder(id, product);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}

	@GetMapping("/getShoppingCart/{id}")
	@ResponseBody
	public ResponseEntity<Order> getShoppingCart(@PathVariable("id") long id) {
		Order order = orderService.getShoppingCart(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
}
