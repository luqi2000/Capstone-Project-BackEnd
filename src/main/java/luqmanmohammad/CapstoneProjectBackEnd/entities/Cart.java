package luqmanmohammad.CapstoneProjectBackEnd.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//this is the class for the elemets in che cart
@Entity
@Table(name = "carts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private int quantity;
	
	//more Cart can have a only one product
	@ManyToOne(fetch = FetchType.LAZY)
	private Product products;
	
	//more elements from cart are related from only one order
	@ManyToOne(fetch = FetchType.LAZY)
	private Order order;
	
}
