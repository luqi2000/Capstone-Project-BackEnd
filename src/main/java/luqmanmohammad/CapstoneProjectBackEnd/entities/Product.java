package luqmanmohammad.CapstoneProjectBackEnd.entities;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private String description;
		private BigDecimal price;
		private String category;
		private String img;
		private Boolean availability;
	
		
		@JsonIgnore
		@OneToMany(mappedBy = "product")
		private List<CartItem> cartItems;
		
		@JsonIgnore
		@OneToMany(mappedBy = "product")
		private List<OrderItem> orderItems;

		@JsonIgnore
	    @ManyToMany(mappedBy = "products")
	    private List<Cart> carts = new ArrayList<>();

		public Product(String name, String description, BigDecimal price, String category, String img,
				Boolean availability, List<CartItem> cartItems, List<OrderItem> orderItems, List<Cart> carts) {
			super();
			this.name = name;
			this.description = description;
			this.price = price;
			this.category = category;
			this.img = img;
			this.availability = availability;
			this.cartItems = cartItems;
			this.orderItems = orderItems;
			this.carts = carts;
		}
		
		public List<Cart> getCarts() {
	        return carts;
	    }

	    public void addCart(Cart cart) {
	        carts.add(cart);
	        cart.getProducts().add(this);
	    }

	    public void removeCart(Cart cart) {
	        carts.remove(cart);
	        cart.getProducts().remove(this);
	    }
}
