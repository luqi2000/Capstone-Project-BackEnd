package luqmanmohammad.CapstoneProjectBackEnd.runners;

import com.github.javafaker.Faker;

import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.ProductPayload;
import luqmanmohammad.CapstoneProjectBackEnd.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ProductRunner implements CommandLineRunner {

    @Autowired
    ProductService productService;


    @Override
    public void run(String... args) {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            // Genera dati casuali per il prodotto
            String name = faker.commerce().productName();
            String description = faker.lorem().sentence();
            BigDecimal price = new BigDecimal(faker.number().randomDouble(2, 1, 100));
            String category = getRandomCategory(faker);
            String img = generateRandomImageUrl(faker, i); // Passa l'indice come parametro
            boolean availability = faker.random().nextBoolean();

            // Crea un nuovo oggetto ProductPayload
            ProductPayload product = new ProductPayload(name, description, price, category, img, availability);

            // Salva il prodotto utilizzando il ProductService
            //productService.create(product);
        }
    }

    private String getRandomCategory(Faker faker) {
        String[] categories = {"giocattoli", "articoli da regalo", "gioielleria"};
        return faker.options().option(categories);
    }

    private String generateRandomImageUrl(Faker faker, int index) {
        // Componi l'URL completo di Unsplash Source con le dimensioni, la categoria e l'indice desiderati
        String imageUrl = String.format("https://source.unsplash.com/800x600/?product,%s,%d",
                faker.options().option("random"), index);

        return imageUrl;
    }
}