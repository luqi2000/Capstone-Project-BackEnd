package luqmanmohammad.CapstoneProjectBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.services.CartService;
import luqmanmohammad.CapstoneProjectBackEnd.services.OrderService;
import luqmanmohammad.CapstoneProjectBackEnd.services.ProductService;
import luqmanmohammad.CapstoneProjectBackEnd.services.UserService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserService userService;
	
	@Autowired 
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
//	//create CartItem
//	@PostMapping("")
//	public Cart CreateCart(@RequestBody Cart cartItem) {
//		return cartService.create(cartItem);
//	}
	
	@PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Long productId, @RequestParam int quantity, @RequestParam Long userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        Product product = productService.findById(productId);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        cartService.addItemToCart(user, product, quantity);
        return ResponseEntity.ok("Prodotto aggiunto al carrello con successo");
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
	
	//get CartItem by id
	@GetMapping("/{id}")
	public Cart getCartById(@PathVariable Long id) {
		return cartService.findById(id);
	}
	
	//modify CartItem
	@PutMapping("/{id}")
	public Cart updateCart(@PathVariable Long id, @RequestBody Cart cartItem) {
		return cartService.findByIdAndUpdate(id, cartItem);
	}
	
	//delete CartItem
	@DeleteMapping("{id}")
	public void getCartByIdAndDelete(@PathVariable Long id) {
		cartService.findByIdAndDelete(id);
	}
}
