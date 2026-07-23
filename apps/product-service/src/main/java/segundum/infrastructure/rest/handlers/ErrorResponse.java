package segundum.infrastructure.rest.handlers;

/**
 * Error response DTO for API error responses.
 * Follows the same format as the user-service.
 */
public class ErrorResponse {

	/** The HTTP status code of the error. */
	private int code;

	/** The HTTP status text of the error. */
	private String error;

	/** A human-readable description of the error. */
	private String message;

	public ErrorResponse() {
	}

	public ErrorResponse(int code, String error, String message) {
		this.code = code;
		this.error = error;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

	public String getMessage() {
		return message;
	}

}
