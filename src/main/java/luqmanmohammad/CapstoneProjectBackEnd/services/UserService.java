package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.UserRegistrationPayload;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.BadRequestException;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.NotFoundException;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository userRepo;
	
	// 1. create user
	public void create(UserRegistrationPayload a) {
		userRepo.findByEmail(a.getEmail()).ifPresent(user -> {
			throw new BadRequestException("email already register");
		});
		
		User user = new User(a.getName(), a.getSurname(), a.getEmail(), a.getPassword(),a.getAddress(), a.getPhoneNumber(), null, null);
		userRepo.save(user);
	}
	
	// 2. search all users
	public List<User> findAll(){
		return userRepo.findAll();
	}
	
	//3 search by id
	public User findById(long id) throws NotFoundException {
		return userRepo.findById(id).orElseThrow(() -> new NotFoundException("user not found!"));
	}
	
	//3.2 search by email
	public User findByEmail(String email) throws NotFoundException {
		return userRepo.findByEmail(email).orElseThrow(() -> new NotFoundException("email not found"));
	}
	
	// 4. find by id and update
	public User findByIdAndUpdate(long id, UserRegistrationPayload body) throws NotFoundException {
		User found = this.findById(id);

		found.setId(id);
		found.setName(body.getName());
		found.setSurname(body.getSurname());
		found.setEmail(body.getEmail());
		found.setAddress(body.getAddress());
		found.setPassword(body.getPassword());
		found.setPhoneNumber(body.getPhoneNumber());

		return userRepo.save(found);
	}
	
	//5. find by id and delete
	public void findByIdAndDelete(long id) throws NotFoundException {
		User found = this.findById(id);
		userRepo.delete(found);
	}
	
}
