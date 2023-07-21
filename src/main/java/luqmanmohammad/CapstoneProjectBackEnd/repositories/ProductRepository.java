package luqmanmohammad.CapstoneProjectBackEnd.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findByName(String name);
}
