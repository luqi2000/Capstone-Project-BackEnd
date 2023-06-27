package luqmanmohammad.CapstoneProjectBackEnd.entities.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserRegistrationPayload {
	@NotNull(message = "name is required")
	private String name;
	@NotNull(message = "surname is required")
	private String surname;
	@NotNull(message = "email is required")
	@Email(message = "email not valid")
	private String email;
	@NotNull(message = "password is required")
	//@Size(min = 10, max = 30, message = "password not valid. Min 10 characters and max 30 characters")
	private String password;
	@NotNull(message = "address is required")
	private String address;
	@NotNull(message = "phone number is required")
	private String phoneNumber;
	
	public UserRegistrationPayload(@NotNull(message = "name is required") String name,
			@NotNull(message = "surname is required") String surname,
			@NotNull(message = "email is required") @Email(message = "email not valid") String email,
			@NotNull(message = "password is required") String password,
			@NotNull(message = "address is required") String address,
			@NotNull(message = "phone number is required") String phoneNumber) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
}
