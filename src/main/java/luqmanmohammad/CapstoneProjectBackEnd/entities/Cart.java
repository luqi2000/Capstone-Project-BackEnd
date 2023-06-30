package luqmanmohammad.CapstoneProjectBackEnd.entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Cart represents an item in a user's shopping cart during their shopping session.
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
    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<CartItem> cartItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @JsonIgnore
    @OneToOne(mappedBy = "cart", fetch = FetchType.LAZY)
    private User user;
    
    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();
    
    @JsonIgnore
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "cart_products")
    private List<Product> products = new ArrayList<>();
    
    public Cart(User user) {
        this.user = user;
        this.cartItems = new ArrayList<>();
    }
    
    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
        product.getCarts().add(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.getCarts().remove(this);
    }

    public void addItem(Product product, int quantity) {
        CartItem existingCartItem = getCartItemByProduct(product);

        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + quantity);
        } else {
            CartItem cartItem = new CartItem(quantity, product);
            addCartItem(cartItem);
        }
    }

    public void updateProductQuantity(Product product, int quantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().equals(product)) {
                item.updateQuantity(quantity);
                return;
            }
        }

        addItem(product, quantity);
    }

    public void removeItem(Product product) {
        CartItem cartItem = getCartItemByProduct(product);
        if (cartItem != null) {
            cartItems.remove(cartItem);
            cartItem.setCart(null);
        }
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public void removeCartItem(CartItem cartItem) {
        cartItems.remove(cartItem);
        cartItem.setCart(null);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public int getItemQuantity(Product product) {
        CartItem cartItem = getCartItemByProduct(product);
        return cartItem != null ? cartItem.getQuantity() : 0;
    }

    public CartItem getCartItemByProduct(Product product) {
        return cartItems.stream()
                .filter(cartItem -> cartItem.getProduct().equals(product))
                .findFirst()
                .orElse(null);
    }

    public void clear() {
        cartItems.clear();
    }
    
    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setCart(this);
    }

    public void removeOrderItem(OrderItem orderItem) {
        orderItems.remove(orderItem);
        orderItem.setCart(null);
    }
}