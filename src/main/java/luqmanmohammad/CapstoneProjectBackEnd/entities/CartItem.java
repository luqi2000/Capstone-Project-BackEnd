package luqmanmohammad.CapstoneProjectBackEnd.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;

	 @ManyToOne
	  private Cart cart;
	
	 @ManyToOne
	  private Product product;

	  private int quantity;
	  
	  public CartItem(int quantity, Product product) {
	        this.quantity = quantity;
	        this.product = product;
	    }
}
