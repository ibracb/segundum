package segundum.infrastructure.rest.product.requests;

/**
 * Request for updating an existing product.
 */
public class UpdateProductRequest {

	private Double price;

	private String description;

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
