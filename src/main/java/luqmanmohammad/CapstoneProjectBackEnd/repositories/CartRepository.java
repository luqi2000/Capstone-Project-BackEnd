package luqmanmohammad.CapstoneProjectBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{

}
