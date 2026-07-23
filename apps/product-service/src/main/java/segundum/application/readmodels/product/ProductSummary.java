package segundum.application.readmodels.product;

import java.time.Instant;

public class ProductSummary {

	private final String productId;
	private final String title;
	private final Double price;
	private final Instant publicationDate;
	private final String categoryName;
	private final long views;

	public ProductSummary(String productId, String title, Double price,
			Instant publicationDate, String categoryName, long views) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.publicationDate = publicationDate;
		this.categoryName = categoryName;
		this.views = views;
	}

	public String getProductId() {
		return productId;
	}

	public String getTitle() {
		return title;
	}

	public Double getPrice() {
		return price;
	}

	public Instant getPublicationDate() {
		return publicationDate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public long getViews() {
		return views;
	}

}
