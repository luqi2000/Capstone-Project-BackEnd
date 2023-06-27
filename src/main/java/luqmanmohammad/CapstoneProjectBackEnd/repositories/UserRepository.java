package luqmanmohammad.CapstoneProjectBackEnd.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
}
