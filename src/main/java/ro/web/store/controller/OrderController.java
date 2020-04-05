
package ro.web.store.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ro.web.store.model.Order;
import ro.web.store.service.OrderService;

@Controller
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(path = "/{id}")
	public ResponseEntity<Object> findOrdersByUserId(
		@PathVariable("id") long id)
	{
		List<Order> order = orderService.findOrderByUser(id);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

}
