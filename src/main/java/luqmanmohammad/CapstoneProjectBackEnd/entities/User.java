package luqmanmohammad.CapstoneProjectBackEnd.entities;

import java.util.List;
import java.util.ArrayList;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String surname;
	private String email;
	private String password;
	private String address;
	private String phoneNumber;
	@Enumerated(EnumType.STRING)
	private Role role;
	
	
	public User(String name, String surname, String email, String password, String address, String phoneNumber,
			Role role, List<Order> orders) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.role = role.User; //this mean that when you create a User this will be a normal user and not an Admin
		this.orders = orders;
	}
	
	//one user can have only one Cart
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Cart cart;

	//one user can submit a List of orders
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();
	
	public Cart getCart() {
        return cart;
    }
	
}
