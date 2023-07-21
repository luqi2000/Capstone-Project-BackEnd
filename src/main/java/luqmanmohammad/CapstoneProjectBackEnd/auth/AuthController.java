package luqmanmohammad.CapstoneProjectBackEnd.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.CapstoneProjectBackEnd.auth.payload.AuthenticationSuccessfullPayload;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.UserLoginPayload;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.UserRegistrationPayload;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.UnauthorizedException;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.UserRepository;
import luqmanmohammad.CapstoneProjectBackEnd.services.UserService;


@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	UserService userService;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder bcrypt;

	@PostMapping("/register")
	public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body) {
		body.setPassword(bcrypt.encode(body.getPassword()));
		User createdUser = userService.create(body);
		return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
	}
	
	//for /login we can't use UserRegistrationPayload because we need 
	//only email and password that's why i have created UserLoginPayload with Validation
	//what have to do login? login will receive email and password and return accessToken
	//AuthentificationSuccessfulFullPayload will return a String with inside a Token
	//what is the step to do? 
	//1. Verify if the email is in the database 
	//2. If the email is in the database i have to verify that the password is correct (same as database).
	//3. If everything is correct generate JWT token (i have created a class JWTTools for this)
	//4. If it is not correct send a message "credentials not valid" error 401
	@PostMapping("/login")
	public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body) {
				User user = userService.findByEmail(body.getEmail());
//				if (!body.getPassword().matches(user.getPassword()))
//					throw new UnauthorizedException("Credentials not valid");
				
				if(!bcrypt.matches(body.getPassword(), user.getPassword())) 
					throw new UnauthorizedException("Credentials not valid"); //matches will return a boolean 
				String token = JWTTools.createToken(user);
				Long userId = userService.findIdByEmail(user.getEmail());
				 AuthenticationSuccessfullPayload responsePayload = new AuthenticationSuccessfullPayload(token, userId);
				    return new ResponseEntity<>(responsePayload, HttpStatus.OK);
	}
}