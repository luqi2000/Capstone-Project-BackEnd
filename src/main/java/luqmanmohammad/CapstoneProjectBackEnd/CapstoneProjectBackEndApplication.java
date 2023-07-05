package luqmanmohammad.CapstoneProjectBackEnd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CapstoneProjectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneProjectBackEndApplication.class, args);
	}
	
	
	//this bean will allow React to fetch data from my back-end for all RequestMapping
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/*").allowedOrigins("http://localhost:3000");
			}
		};
	}
	

}
