package luqmanmohammad.CapstoneProjectBackEnd;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class CapstoneProjectBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneProjectBackEndApplication.class, args);
	}
	
//	@Bean
//	public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**")
//                		.allowedOriginPatterns("*")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
//                        .allowedHeaders("Authorization", "Content-Type", "Access-Control-Request-Headers")
//                        .allowCredentials(true);
//	    }
//	  };
//	}
}