package luqmanmohammad.CapstoneProjectBackEnd.runners;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.github.javafaker.Faker;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.ProductPayload;
import luqmanmohammad.CapstoneProjectBackEnd.services.ProductService;

@Component
public class ProductRunner implements CommandLineRunner {

    @Autowired
    ProductService productService;

    private Set<Integer> usedImageIndices = new HashSet<>();

    @Override
    public void run(String... args) {
        Faker faker = new Faker();

        for (int i = 0; i < 30; i++) {
            // Genera dati casuali per il prodotto
            String name = faker.commerce().productName();
            String description = faker.lorem().sentence();
            BigDecimal price = new BigDecimal(faker.number().randomDouble(2, 1, 100));
            String category = getRandomCategory(faker);
            String img = generateUniqueRandomImageUrl(faker, category);
            boolean availability = faker.random().nextBoolean();

            // Crea un nuovo oggetto ProductPayload
            ProductPayload product = new ProductPayload(name, description, price, category, img, availability);

            // Salva il prodotto utilizzando il ProductService
             //productService.create(product);
        }
    }

    private String getRandomCategory(Faker faker) {
        String[] categories = {"Games", "Gifts", "Jewelry"};
        return faker.options().option(categories);
    }

    private String generateUniqueRandomImageUrl(Faker faker, String category) {
        String[] imageCategories = {category, "random"}; // Add "random" category to get a mix of images
        int imageIndex = generateUniqueImageIndex(faker);

        // Compose the URL with the desired category and index
        String imageUrl = String.format("https://source.unsplash.com/800x600/?%s,%d",
                String.join(",", imageCategories), imageIndex);

        return imageUrl;
    }

    private int generateUniqueImageIndex(Faker faker) {
        int maxImageIndex = 200; // Maximum number of available images
        int imageIndex = faker.number().numberBetween(1, maxImageIndex + 1);

        // Keep generating a new image index until it's unique
        while (usedImageIndices.contains(imageIndex)) {
            imageIndex = faker.number().numberBetween(1, maxImageIndex + 1);
        }

        usedImageIndices.add(imageIndex);
        return imageIndex;
    }
}
