package luqmanmohammad.CapstoneProjectBackEnd.auth;

import java.util.Date;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.Keys;
import luqmanmohammad.CapstoneProjectBackEnd.entities.User;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.UnauthorizedException;
import io.jsonwebtoken.ExpiredJwtException;

//this class will generate token so i need static method for create and verify token
//create token means return token that is a String 
@Component
public class JWTTools {
	
	//for create a token we need secret key and expired date of the key which one i have declared 
	//before in a file env.properties and write in .gitignore for not commit of this and then declared 
	//in application.properties the variable
	//When we use static method @value remember that this will not work directly thats's why we have to create 
	//a setter and then use @value with the variable declared in application.properties
	private static String secret;
	private static int expiration;

	@Value("${JWT_SECRET}")
	public void setSecret(String secretKey) {
		secret = secretKey;
	}
	
	@Value("${JWT_EXPIRATION}")
	public void setExpiration(String expirationInDays) {
		expiration = Integer.parseInt(expirationInDays) * 24 * 60 * 60 * 1000; // calculate in milliseconds of expirationInDays
	}
	
	//create token
	//Jwts is a library that we need for doing what i need to do and builder is for create token
	//what we need? we need payload, header, token signature of the token
	//setSubject is the owner of the toker 
	//setIssuedAt is when the token is released
	//setExpiration is when this token expire and it will from today to expirationDay
	//signWith will convert my secret in something that he understand
	//.compact give token
	static public String createToken(User u) {
		String token = Jwts.builder()
				.setSubject(u.getEmail())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(Keys.hmacShaKeyFor(secret.getBytes())).compact();
		return token;
	}

	//verify if the token is valid
	static public void isTokenValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parse(token);

		} catch (MalformedJwtException e) {
			throw new UnauthorizedException("token not valid");
		} catch (ExpiredJwtException e) {
			throw new UnauthorizedException("token expired");
		} catch (Exception e) {
			throw new UnauthorizedException("error with token, please log in again");
		}
	}

	public static String extractSubject(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes())).build().parseClaimsJws(token)
				.getBody().getSubject();
	}
}