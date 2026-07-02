package segundum.infrastructure.rest.responses;

/**
 * Represents an error response returned by the REST API.
 */
public class ErrorResponse {

	/**
	 * The HTTP status code of the error.
	 */
	private int code;

	/**
	 * The HTTP status text of the error.
	 */
	private String error;

	/**
	 * A human-readable description of the error.
	 */
	private String message;

	/**
	 * Constructs a new ErrorResponse with the given parameters.
	 * 
	 * @param code    the HTTP status code of the error
	 * @param error   the HTTP status text of the error
	 * @param message a human-readable description of the error
	 */
	public ErrorResponse(int code, String error, String message) {
		this.code = code;
		this.error = error;
		this.message = message;
	}

	/**
	 * Returns the HTTP status code of the error.
	 * 
	 * @return the HTTP status code of the error
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Returns the HTTP status text of the error.
	 * 
	 * @return the HTTP status text of the error
	 */
	public String getError() {
		return error;
	}

	/**
	 * Returns a human-readable description of the error.
	 * 
	 * @return a human-readable description of the error
	 */
	public String getMessage() {
		return message;
	}

}
