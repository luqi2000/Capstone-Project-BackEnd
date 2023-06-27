package luqmanmohammad.CapstoneProjectBackEnd.runners;

import java.util.Locale;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

@Component
public class ProductRunner implements CommandLineRunner{

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for(int i=0; i<15; i++) {
			try {
				
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
}
