package luqmanmohammad.CapstoneProjectBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;


public interface OrderRepository extends JpaRepository<Order, Long>{

}
