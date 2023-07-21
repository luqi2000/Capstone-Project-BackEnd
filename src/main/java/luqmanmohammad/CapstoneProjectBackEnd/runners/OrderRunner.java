//package luqmanmohammad.CapstoneProjectBackEnd.runners;
//
//import java.util.Date;
//import java.util.NoSuchElementException;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//import luqmanmohammad.CapstoneProjectBackEnd.entities.Order;
//import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
//import luqmanmohammad.CapstoneProjectBackEnd.repositories.OrderRepository;
//import luqmanmohammad.CapstoneProjectBackEnd.services.UserService;
//
//@Component
//public class OrderRunner implements CommandLineRunner{
//	
//	@Autowired
//	UserService userService;
//	
//	@Autowired
//	OrderRepository orderRepo;
//
//	@Override
//	public void run(String... args) throws Exception {
//		Order order = new Order();
//		
//		Optional<User> userOptional = Optional.ofNullable(userService.findById(1));
//		User user = userOptional.orElseThrow(() -> new NoSuchElementException("User not found"));
//		order.setUser(user);
//		order.setOrderDate(new Date());
//		//orderRepo.save(order);
//
//		
//	}
//}
