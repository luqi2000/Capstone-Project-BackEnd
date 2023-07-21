package luqmanmohammad.CapstoneProjectBackEnd.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//OrderItem represents an item of an order placed and stored in the system. 
//When a user completes the purchase and places the order, the Cart are
//converted into OrderItems and are associated with the specific order. 
//OrderItems are then permanently registered in the system as part of 
//the order and contain information such as product, quantity ordered and unit price.

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
    private double unitPrice;
	
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	
    @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	private Cart cart;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Product product;
	
	public OrderItem(int quantity, Product product) {
        this.quantity = quantity;
        this.product = product;
    }

}
