package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;
import luqmanmohammad.CapstoneProjectBackEnd.entities.CartItem;
import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
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
	
	public void addItemToCart(User user, Product product, int quantity) {
        Cart cart = user.getCart();

        CartItem existingCartItem = cart.getCartItemByProduct(product);

        if (existingCartItem != null) {
            // If the product already exists in the cart, update the quantity
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(quantity, product);
            cart.addCartItem(cartItem);
        }

        // Save the updated cart in the database
        cartRepo.save(cart);
    }

}
