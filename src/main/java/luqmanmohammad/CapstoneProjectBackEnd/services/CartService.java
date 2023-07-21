package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.NotFoundException;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.CartRepository;

@Service
public class CartService {
	@Autowired
	CartRepository cartRepo;
	
	// 1. create CartItem
	public Cart create(Cart a) {
		return cartRepo.save(a);
	}
	
	// 2. search all cartItem
	public List<Cart> findAll(){
		return cartRepo.findAll();
	}
	
	//3 search cartItem by id
	public Cart findById(long id) throws NotFoundException {
		return cartRepo.findById(id).orElseThrow(() -> new NotFoundException("cartItem not found!"));
	}
	
	// 4. find by id and update
	public Cart findByIdAndUpdate(long id, Cart body) throws NotFoundException {
		Cart found = this.findById(id);
		found.setId(id);
		found.setQuantity(body.getQuantity());
		return cartRepo.save(found);
	}
	
	//5. find by id and delete
	public void findByIdAndDelete(long id) throws NotFoundException {
		Cart found = this.findById(id);
		cartRepo.delete(found);
	}
	
	public void addItemToCart(Cart cart, Product product, int quantity) {
        cart.addItem(product, quantity);
        cartRepo.save(cart);
    }
}
