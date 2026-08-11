package segundum.infrastructure.rest.handlers;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Represents the standard error response returned by the API.
 */
@Schema(description = "Standard error response returned by the API")
public class ErrorResponse {
	
	/**
	 * The HTTP status code.
	 */
	@Schema(description = "HTTP status code", example = "XXX")
	private int code;
	
	/**
	 * The HTTP status reason phrase.
	 */
	@Schema(description = "HTTP status reason phrase", example = "Error")
	private String error;
	
	/**
	 * The human-readable error message.
	 */
	@Schema(description = "Human-readable error message", example = "Description of the problem")
	private String message;

	/**
	 * Constructs a new ErrorResponse with the given data.
	 *
	 * @param code    the HTTP status code
	 * @param error   the HTTP status reason phrase
	 * @param message the human-readable error message
	 */
	public ErrorResponse(int code, String error, String message) {
		this.code = code;
		this.error = error;
		this.message = message;
	}

	/**
	 * Returns the HTTP status code.
	 *
	 * @return the HTTP status code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Returns the HTTP status reason phrase.
	 *
	 * @return the HTTP status reason phrase
	 */
	public String getError() {
		return error;
	}

	/**
	 * Returns the human-readable error message.
	 *
	 * @return the human-readable error message
	 */
	public String getMessage() {
		return message;
	}

}
