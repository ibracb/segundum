package segundum.infrastructure.rest.handlers;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Error response DTO for API error responses.
 * Follows the same format as the other services.
 */
@Schema(description = "Standard error response returned by the API")
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "XXX")
    private int code;

    @Schema(description = "HTTP status reason phrase", example = "Error")
    private String error;

    @Schema(description = "Human-readable error message", example = "Description of the problem")
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
