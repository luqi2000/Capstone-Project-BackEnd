package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.UserRegistrationPayload;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	public void create(UserRegistrationPayload a) {
		User user = new User(a.getName(), a.getSurname(), a.getEmail(), a.getPassword(),a.getAddress(), a.getPhoneNumber(), null, null);
		userRepo.save(user);
	}
	
	public List<User> findAll(){
		return userRepo.findAll();
	}
}
