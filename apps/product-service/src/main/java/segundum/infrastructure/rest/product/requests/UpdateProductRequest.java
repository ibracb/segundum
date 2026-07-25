package segundum.infrastructure.rest.product.requests;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to update an existing product")
public class UpdateProductRequest {

	@Schema(description = "New price", example = "249.99")
	private Double price;

	@Schema(description = "New description", example = "Updated description")
	private String description;

	@Schema(description = "New condition status", example = "LIKE_NEW")
	private String conditionStatus;

	/**
	 * Default constructor required by JSON deserialization.
	 */
	public UpdateProductRequest() {
		// Default constructor to satisfy JSON deserialization requirements
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getConditionStatus() {
		return conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

}
