package luqmanmohammad.CapstoneProjectBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CartItem {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	
	 @JsonIgnore
	 @ManyToOne
	 private Cart cart;
	
	 @ManyToOne
	 private Product product;

	 private int quantity;
	  
	 public CartItem(int quantity, Product product) {
	        this.quantity = quantity;
	        this.product = product;
	 }
	 
	 public CartItem(Product product, int quantity) {
	        this.product = product;
	        this.quantity = quantity;
	 }
	 
	 public void setCart(Cart cart) {
		    this.cart = cart;
	 }

	 public void updateQuantity(int newQuantity) {
	        this.quantity = newQuantity;
	 }	
}
