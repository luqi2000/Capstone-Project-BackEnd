package luqmanmohammad.CapstoneProjectBackEnd.runners;

import java.math.BigDecimal;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.github.javafaker.Faker;

import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.ProductPayload;
import luqmanmohammad.CapstoneProjectBackEnd.services.ProductService;

@Component
public class ProductRunner implements CommandLineRunner{
	
	@Autowired
	ProductService productService;

	@Override
	public void run(String... args) throws Exception {
		Faker faker = new Faker(new Locale("it"));
		for(int i=0; i<20; i++) {
			try {
				String name = faker.name().firstName() + "'s Toy";
		        String description = faker.lorem().sentence();
		        BigDecimal price = new BigDecimal(faker.number().randomDouble(2, 1, 100));
		        String category = faker.options().option("Action Figures", "Dolls", "Puzzles", "Plush Toys", "Vehicles");
		        String imageUrl = faker.internet().image();
		        boolean availability = faker.random().nextBoolean();
		        
		        ProductPayload product = new ProductPayload(name, description, price, category, imageUrl, availability);
		        //productService.create(product);
		        
			}catch(Exception e) {
				System.out.println(e);
			}
		}
		
	}
	
}
