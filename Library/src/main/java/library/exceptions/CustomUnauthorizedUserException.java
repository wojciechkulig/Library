package library.exceptions;

public class CustomUnauthorizedUserException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CustomUnauthorizedUserException(String message) {
		super(message);
	}
}
