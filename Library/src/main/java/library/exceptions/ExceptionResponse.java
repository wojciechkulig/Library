package library.exceptions;

import java.time.LocalDateTime;

public class ExceptionResponse {
	private LocalDateTime dateTime;
	private String message;
	private String details;
	
	public ExceptionResponse(LocalDateTime dateTime,String message,String details) {
		this.dateTime = dateTime;
		this.message = message;
		this.details = details;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public String getMessage() {
		return message;
	}

	public String getDetails() {
		return details;
	}

	
	
}
