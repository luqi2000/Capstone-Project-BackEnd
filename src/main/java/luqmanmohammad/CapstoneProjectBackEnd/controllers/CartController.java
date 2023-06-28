package luqmanmohammad.CapstoneProjectBackEnd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;
import luqmanmohammad.CapstoneProjectBackEnd.services.CartService;


@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	CartService cartService;
	
	//create CartItem
	@PostMapping("")
	public Cart CreateCart(@RequestBody Cart cartItem) {
		return cartService.create(cartItem);
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
