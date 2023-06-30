package luqmanmohammad.CapstoneProjectBackEnd.entities;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date orderDate;
	
	//more orders can submit by one User
	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
	
	//one order can have more elements from cart
	@OneToMany(mappedBy = "order")
	List<Cart> cart;
	
    public Order(User user) {
        this.user = user;
    }
	
    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

}
