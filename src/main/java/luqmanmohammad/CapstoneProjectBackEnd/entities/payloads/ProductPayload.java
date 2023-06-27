package luqmanmohammad.CapstoneProjectBackEnd.entities.payloads;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPayload {
	@NotNull(message = "name is required")
	private String name;
	@NotNull(message = "description is required")
	private String description;
	@NotNull(message = "price is required")
	private BigDecimal price;
	@NotNull(message = "category is required")
	private String category;
	@NotNull(message = "img is required")
	private String img;
	@NotNull(message = "availability is required")
	private int availability;
	
	public ProductPayload(
			@NotNull(message = "name is required") String name,
			@NotNull(message = "description is required") String description,
			@NotNull(message = "price is required") BigDecimal price,
			@NotNull(message = "category is required") String category,
			@NotNull(message = "img is required") String img,
			@NotNull(message = "availability is required") int availability) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
		this.img = img;
		this.availability = availability;
	}
}
