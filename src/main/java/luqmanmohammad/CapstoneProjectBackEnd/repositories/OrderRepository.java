package luqmanmohammad.CapstoneProjectBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
