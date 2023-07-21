package luqmanmohammad.CapstoneProjectBackEnd.auth.payload;

import lombok.Data;

@Data
public class AuthenticationSuccessfullPayload {
	private String accessToken;
	private String userId;
	
	public AuthenticationSuccessfullPayload(String accessToken, Long userId) {
		super();
		this.accessToken = accessToken;
		this.userId = userId.toString();
	}
}