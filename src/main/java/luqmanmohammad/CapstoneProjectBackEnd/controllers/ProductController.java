package luqmanmohammad.CapstoneProjectBackEnd.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.ProductPayload;
import luqmanmohammad.CapstoneProjectBackEnd.services.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController{
	
	@Autowired
	ProductService productService;
	
	@PostMapping("")
	public Product createProduct(@RequestBody @Validated ProductPayload p) {
		return productService.create(p);
	}
	
	@GetMapping("")
    public List<Product> getAllProducts() {
        return productService.findAll();
    }
	
	@GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.findById(id);
    }
	
	@PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody @Validated ProductPayload product) {
        return productService.findByIdAndUpdate(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.findByIdAndDelete(id);
    }
}
