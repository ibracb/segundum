package segundum.infrastructure.rest.product.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Request for creating a new product.
 */
public class CreateProductRequest {

	@NotNull
	@NotBlank
	private String title;

	@NotNull
	@NotBlank
	private String description;

	@PositiveOrZero
	private double price;

	@NotNull
	@NotBlank
	private String conditionStatus;

	@NotNull
	@NotBlank
	private String categoryId;

	private boolean shippingAvailable;

	@NotNull
	@NotBlank
	private String sellerId;

	/**
	 * Default constructor required by JSON deserialization.
	 */
	public CreateProductRequest() {
		// Default constructor to satisfy JSON deserialization requirements
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getConditionStatus() {
		return conditionStatus;
	}

	public void setConditionStatus(String conditionStatus) {
		this.conditionStatus = conditionStatus;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public boolean isShippingAvailable() {
		return shippingAvailable;
	}

	public void setShippingAvailable(boolean shippingAvailable) {
		this.shippingAvailable = shippingAvailable;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

}
