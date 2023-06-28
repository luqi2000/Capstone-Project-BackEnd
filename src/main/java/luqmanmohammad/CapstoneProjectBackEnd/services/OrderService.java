package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;
import luqmanmohammad.CapstoneProjectBackEnd.entities.OrderItem;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.NotFoundException;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.OrderRepository;

@Service
public class OrderService {
	@Autowired
	OrderRepository orderRepo;
	
	// 1. create Order
//	public Order create(Order a) {
//		return orderRepo.save(a);
//	}
	
    public Order createOrderFromCart(User user) {
        Cart cart = user.getCart();
        List<OrderItem> orderItems = cart.getOrderItems();

        Order order = new Order(user);

        for (OrderItem orderItem : orderItems) {
            Product product = orderItem.getProduct();
            int quantity = orderItem.getQuantity();

            OrderItem newOrderItem = new OrderItem(quantity, product);
            order.addOrderItem(newOrderItem);
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
