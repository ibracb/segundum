package segundum.infrastructure.rest.handlers;

/**
 * Represents an error response returned by the REST API.
 */
public class ErrorResponse {

	private int code;

	private String error;

	private String message;

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
