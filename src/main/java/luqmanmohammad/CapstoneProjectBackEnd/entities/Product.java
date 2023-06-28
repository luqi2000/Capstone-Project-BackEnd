package luqmanmohammad.CapstoneProjectBackEnd.entities;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
		
		
		//one product can have multiple shopping card
		@OneToMany(mappedBy = "products")
		private List<Cart> card;
		
		@OneToMany(mappedBy = "product")
		private List<OrderItem> orderItems;
		
		@OneToMany(mappedBy = "product")
		private List<CartItem> cartItem;


		public Product(String name, String description, BigDecimal price, String category, String img,
				boolean availability, List<Cart> card) {
			super();
			this.name = name;
			this.description = description;
			this.price = price;
			this.category = category;
			this.img = img;
			this.availability = availability;
			this.card = card;
		}
}
