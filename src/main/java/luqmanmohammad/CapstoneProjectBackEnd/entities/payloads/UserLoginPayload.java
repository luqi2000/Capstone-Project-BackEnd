package luqmanmohammad.CapstoneProjectBackEnd.entities.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserLoginPayload {
	@NotNull
	@Email(message = "email not valid")
	String email;
	@NotNull(message = "password required")
	@Size(min = 10, max = 30, message = "password not valid. Min 10 characters and max 30 characters")
	String password;
}