package segundum.infrastructure.rest.product.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Request to create a new product")
public class CreateProductRequest {

	@NotNull
	@NotBlank
	@Schema(description = "Product title", example = "iPhone 12")
	private String title;

	@NotNull
	@NotBlank
	@Schema(description = "Product description", example = "Excellent condition, includes charger")
	private String description;

	@PositiveOrZero
	@Schema(description = "Product price", example = "299.99")
	private double price;

	@NotNull
	@NotBlank
	@Schema(description = "Product condition status", example = "GOOD")
	private String conditionStatus;

	@NotNull
	@NotBlank
	@Schema(description = "Category identifier", example = "1")
	private String categoryId;

	@Schema(description = "Whether shipping is available", example = "true")
	private boolean shippingAvailable;

	@NotNull
	@NotBlank
	@Schema(description = "Seller identifier", example = "a1b2c3d4-e5f6-7890-abcd-ef1234567890")
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
