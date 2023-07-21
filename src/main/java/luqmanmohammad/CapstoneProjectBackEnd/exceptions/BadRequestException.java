package luqmanmohammad.CapstoneProjectBackEnd.exceptions;

public class BadRequestException extends RuntimeException {
	public BadRequestException(String message) {
		super(message);
	}
}