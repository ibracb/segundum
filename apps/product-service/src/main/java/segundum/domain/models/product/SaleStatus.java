package segundum.domain.models.product;

/**
 * Represents the sale status of a product.
 */
public enum SaleStatus {

	/**
	 * The product is in draft status (not published).
	 */
	DRAFT,

	/**
	 * The product is currently for sale.
	 */
	FOR_SALE,

	/**
	 * The product has been reserved.
	 */
	RESERVED,

	/**
	 * The product has been sold.
	 */
	SOLD,

	/**
	 * The product has been permanently deleted.
	 */
	DELETED

}
