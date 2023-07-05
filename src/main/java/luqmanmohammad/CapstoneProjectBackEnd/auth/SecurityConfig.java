package luqmanmohammad.CapstoneProjectBackEnd.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//1. start from SecurityConfig as a class that return SecurityFilterChain
//SecurityFilterChain want as a parameter HttpSecurity as a object and HttpSecurity permit 
//to configure the security so i can authorize or refuse configuration with a lambda expression
//.authenticated mean authentification for using, in reality in default everything need authentification
//example: all the enpoint /auth and with this /** it mean also subsequences 
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	JWTAuthFilter jwtAuthFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http.csrf(c -> c.disable());
	
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/user/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/cart/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/orders/**").authenticated());
		http.authorizeHttpRequests(auth -> auth.requestMatchers("/products/**").permitAll());
		
		http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class); //i want to putjwtAuthFilter in a specific point 
		//disactivated session because we are using in this case JWT so whit stateless it mean without state/session 
		http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); 

		return http.build(); // when you finish all the configuration settings is important return http.build
	}
	
	@Bean
	PasswordEncoder pwEncoder() {
		return new BCryptPasswordEncoder(10); //10 is the velocity of the result more high it is more slow it will be but is better to have a little bit slow
	}
}