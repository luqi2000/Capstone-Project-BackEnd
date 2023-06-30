package luqmanmohammad.CapstoneProjectBackEnd.auth.payload;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthenticationSuccessfullPayload {
	private String accessToken;
}