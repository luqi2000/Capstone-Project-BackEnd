package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;
import luqmanmohammad.CapstoneProjectBackEnd.entities.CartItem;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;
import luqmanmohammad.CapstoneProjectBackEnd.entities.OrderItem;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.NotFoundException;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.OrderRepository;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.UserRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepo;
	
	@Autowired
	UserRepository userRepo;
	
	// 1. create Order
//	public Order create(Order a) {
//		return orderRepo.save(a);
//	}
	
	
	
	public Order createOrderFromCart(User user) {
	    Cart cart = user.getCart();
	    if (cart == null) {
	        throw new IllegalArgumentException("Il carrello dell'utente è nullo. Impossibile creare l'ordine.");
	    }

	    List<CartItem> cartItems = cart.getCartItems();
	    if (cartItems.isEmpty()) {
	        throw new IllegalArgumentException("Il carrello dell'utente è vuoto. Impossibile creare l'ordine.");
	    }

	    Order order = new Order(user);
	    Date orderDate = new Date();
	    order.setOrderDate(orderDate);

	    for (CartItem cartItem : cartItems) {
	        Product product = cartItem.getProduct();
	        int quantity = cartItem.getQuantity();

	        OrderItem orderItem = new OrderItem(quantity, product);
	        order.addOrderItem(orderItem);
	    }

	    Order savedOrder = orderRepo.save(order);
	    return savedOrder;
	}
	
	// 2. search all orders
	public List<Order> findAll(){
		return orderRepo.findAll();
	}
	
	//3 search order by id
	public Order findById(long id) throws NotFoundException {
		return orderRepo.findById(id).orElseThrow(() -> new NotFoundException("order not found!"));
	}
	
	// 4. find by id and update
	public Order findByIdAndUpdate(long id, Order body) throws NotFoundException {
		Order found = this.findById(id);

		found.setId(id);
		found.setCart(body.getCart());
		found.setOrderDate(body.getOrderDate());
		found.setUser(body.getUser());

		return orderRepo.save(found);
	}
	
	//5. find by id and delete
	public void findByIdAndDelete(long id) throws NotFoundException {
		Order found = this.findById(id);
		orderRepo.delete(found);
	}
}
