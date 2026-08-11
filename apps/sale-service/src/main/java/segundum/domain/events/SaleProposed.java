package segundum.domain.events;

import segundum.domain.models.sale.DateTime;
import segundum.domain.models.sale.PickupLocation;
import segundum.domain.models.sale.Price;
import segundum.domain.models.sale.ProductId;
import segundum.domain.models.sale.PurchaserId;
import segundum.domain.models.sale.PurchaserName;
import segundum.domain.models.sale.PurchaserSurname;
import segundum.domain.models.sale.Sale;
import segundum.domain.models.sale.SaleId;
import segundum.domain.models.sale.SellerId;
import segundum.domain.models.sale.SellerName;
import segundum.domain.models.sale.SellerSurname;
import segundum.domain.models.sale.Title;

/**
 * Represents a domain event raised when a sale is proposed.
 */
public class SaleProposed extends DomainEvent implements SaleEvent {

    /**
     * The identifier of the sale.
     */
    private final SaleId saleId;

    /**
     * The identifier of the product.
     */
    private final ProductId productId;

    /**
     * The identifier of the seller.
     */
    private final SellerId sellerId;

    /**
     * The name of the seller.
     */
    private final SellerName sellerName;

    /**
     * The surname of the seller.
     */
    private final SellerSurname sellerSurname;

    /**
     * The identifier of the purchaser.
     */
    private final PurchaserId purchaserId;

    /**
     * The name of the purchaser.
     */
    private final PurchaserName purchaserName;

    /**
     * The surname of the purchaser.
     */
    private final PurchaserSurname purchaserSurname;

    /**
     * The price of the sale.
     */
    private final Price price;

    /**
     * The title of the sale.
     */
    private final Title title;

    /**
     * The pickup location of the sale.
     */
    private final PickupLocation pickupLocation;

    /**
     * The date and time of the sale.
     */
    private final DateTime datetime;

    /**
     * Constructs a new SaleProposed event.
     *
     * @param saleId            the identifier of the sale
     * @param productId         the identifier of the product
     * @param sellerId          the identifier of the seller
     * @param sellerName        the name of the seller
     * @param sellerSurname     the surname of the seller
     * @param purchaserId       the identifier of the purchaser
     * @param purchaserName     the name of the purchaser
     * @param purchaserSurname  the surname of the purchaser
     * @param price             the price of the sale
     * @param title             the title of the sale
     * @param pickupLocation    the pickup location of the sale
     * @param datetime          the date and time of the sale
     */
    public SaleProposed(SaleId saleId, ProductId productId, SellerId sellerId,
            SellerName sellerName, SellerSurname sellerSurname, PurchaserId purchaserId,
            PurchaserName purchaserName, PurchaserSurname purchaserSurname,
            Price price, Title title, PickupLocation pickupLocation, DateTime datetime) {
        super();
        this.saleId = saleId;
        this.productId = productId;
        this.sellerId = sellerId;
        this.sellerName = sellerName;
        this.sellerSurname = sellerSurname;
        this.purchaserId = purchaserId;
        this.purchaserName = purchaserName;
        this.purchaserSurname = purchaserSurname;
        this.price = price;
        this.title = title;
        this.pickupLocation = pickupLocation;
        this.datetime = datetime;
    }

    /**
     * Returns the identifier of the sale.
     *
     * @return the identifier of the sale
     */
    public SaleId getSaleId() {
        return saleId;
    }

    /**
     * Returns the identifier of the product.
     *
     * @return the identifier of the product
     */
    public ProductId getProductId() {
        return productId;
    }

    /**
     * Returns the identifier of the seller.
     *
     * @return the identifier of the seller
     */
    public SellerId getSellerId() {
        return sellerId;
    }

    /**
     * Returns the name of the seller.
     *
     * @return the name of the seller
     */
    public SellerName getSellerName() {
        return sellerName;
    }

    /**
     * Returns the surname of the seller.
     *
     * @return the surname of the seller
     */
    public SellerSurname getSellerSurname() {
        return sellerSurname;
    }

    /**
     * Returns the identifier of the purchaser.
     *
     * @return the identifier of the purchaser
     */
    public PurchaserId getPurchaserId() {
        return purchaserId;
    }

    /**
     * Returns the name of the purchaser.
     *
     * @return the name of the purchaser
     */
    public PurchaserName getPurchaserName() {
        return purchaserName;
    }

    /**
     * Returns the surname of the purchaser.
     *
     * @return the surname of the purchaser
     */
    public PurchaserSurname getPurchaserSurname() {
        return purchaserSurname;
    }

    /**
     * Returns the price of the sale.
     *
     * @return the price of the sale
     */
    public Price getPrice() {
        return price;
    }

    /**
     * Returns the title of the sale.
     *
     * @return the title of the sale
     */
    public Title getTitle() {
        return title;
    }

    /**
     * Returns the pickup location of the sale.
     *
     * @return the pickup location of the sale
     */
    public PickupLocation getPickupLocation() {
        return pickupLocation;
    }

    /**
     * Returns the date and time of the sale.
     *
     * @return the date and time of the sale
     */
    public DateTime getDatetime() {
        return datetime;
    }

    @Override
    public void dispatch(Sale sale) {
        sale.when(this);
    }

}
