package luqmanmohammad.CapstoneProjectBackEnd.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import luqmanmohammad.CapstoneProjectBackEnd.entities.Product;
import luqmanmohammad.CapstoneProjectBackEnd.entities.payloads.ProductPayload;
import luqmanmohammad.CapstoneProjectBackEnd.exceptions.NotFoundException;
import luqmanmohammad.CapstoneProjectBackEnd.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	ProductRepository productRepo;
	
	// 1. create product
	public void create(ProductPayload a) {
		Product product = new Product(a.getName(), a.getDescription(), a.getPrice(), a.getCategory(), a.getImg(),true, null);
		productRepo.save(product);
	}
	
	// 2. search all product
	public List<Product> findAll(){
		return productRepo.findAll();
	}
	
	//3 search product by id
	public Product findById(long id) throws NotFoundException {
		return productRepo.findById(id).orElseThrow(() -> new NotFoundException("product by id not found!"));
	}
	
	//3.2 search product by name
	public Product findByName(String name) throws NotFoundException {
		return productRepo.findByName(name).orElseThrow(() -> new NotFoundException("product by name not found"));
	}
	
	// 4. find by id and update
	public Product findByIdAndUpdate(long id, ProductPayload body) throws NotFoundException {
		Product found = this.findById(id);

		found.setId(id);
		found.setName(body.getName());
		found.setDescription(body.getDescription());
		found.setPrice(body.getPrice());
		found.setCategory(body.getCategory());
		found.setImg(body.getImg());
		found.setAvailability(false);
		
		return productRepo.save(found);
	}
	
	//5. find product by id and delete
	public void findByIdAndDelete(long id) throws NotFoundException {
		Product found = this.findById(id);
		productRepo.delete(found);
	}
	
}
