package luqmanmohammad.CapstoneProjectBackEnd.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.services.OrderService;
import luqmanmohammad.CapstoneProjectBackEnd.services.UserService;

@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	OrderService orderService;
	@Autowired
	UserService userService;
	
//	@PostMapping("")
//	public Order createOrder(Order order) {
//		return orderService.create(order);
//	}
    @PostMapping("")
    public ResponseEntity<Order> createOrder(@RequestBody Map<String, Long> request) {
        Long userId = request.get("userId");
        if (userId == null) {
            return ResponseEntity.badRequest().build();
        }

        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            Order order = orderService.createOrderFromCart(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(order);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
	
    @PostMapping("/order")
    public ResponseEntity<Order> createOrderFromCart(@RequestParam Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Order order = orderService.createOrderFromCart(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(order);
    }
	 
	@GetMapping("")
    public List<Order> getAllOrders() {
        return orderService.findAll();
    }
	
	@GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.findById(id);
    }
	
    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order updatedOrder) {
        return orderService.findByIdAndUpdate(id, updatedOrder);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.findByIdAndDelete(id);
    }
}
