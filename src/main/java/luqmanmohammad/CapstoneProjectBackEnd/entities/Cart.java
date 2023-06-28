package luqmanmohammad.CapstoneProjectBackEnd.entities;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.ArrayList;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Product products;

    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    public Cart(User user) {
        this.user = user;
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

    public void removeItem(Product product) {
        CartItem cartItem = getCartItemByProduct(product);
        if (cartItem != null) {
            cartItems.remove(cartItem);
            cartItem.setCart(null);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setCart(this);
    }

    public void updateItemQuantity(Product product, int quantity) {
        CartItem cartItem = getCartItemByProduct(product);

        if (cartItem != null) {
            if (quantity > 0) {
                cartItem.setQuantity(quantity);
            } else {
                removeItem(product);
            }
        } else {
            if (quantity > 0) {
                addItem(product, quantity);
            }
        }
    }

    public void clear() {
        cartItems.clear();
    }

    public List<Product> getCartItems() {
        return cartItems.stream()
                .map(CartItem::getProduct)
                .collect(Collectors.toList());
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

    public void addCartItem(CartItem cartItem) {
        cartItems.add(cartItem);
        cartItem.setCart(this);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }
}