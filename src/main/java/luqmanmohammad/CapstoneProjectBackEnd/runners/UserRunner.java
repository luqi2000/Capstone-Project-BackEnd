package luqmanmohammad.CapstoneProjectBackEnd.runners;

import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.UserRegistrationPayload;
import luqmanmohammad.CapstoneProjectBackEnd.services.UserService;


@Component
public class UserRunner implements CommandLineRunner{
	
	@Autowired
	UserService userService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for (int i = 0; i < 2; i++) {
			try {
				String name = faker.name().firstName();
				String surname = faker.name().lastName();
				String email = faker.internet().emailAddress();
				String password  = faker.code().asin();
				String address = faker.address().fullAddress();
				String phoneNumber = faker.phoneNumber().phoneNumber();
				
				
				UserRegistrationPayload user = new UserRegistrationPayload( name, surname, email, password, address, phoneNumber);
				userService.create(user);
			
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		
	}

}


