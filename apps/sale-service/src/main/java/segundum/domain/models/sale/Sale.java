package segundum.domain.models.sale;

import java.util.ArrayList;
import java.util.List;

import segundum.domain.events.DomainEvent;
import segundum.domain.events.SaleCancelledByPurchaser;
import segundum.domain.events.SaleCancelledBySeller;
import segundum.domain.events.SaleCompleted;
import segundum.domain.events.SaleEvent;
import segundum.domain.events.SaleRejected;
import segundum.domain.events.SaleProposed;
import segundum.domain.events.SaleReserved;
import segundum.domain.exceptions.sale.status.SaleNotPendingException;
import segundum.domain.exceptions.sale.creation.PurchaserCannotBeSellerException;
import segundum.domain.exceptions.sale.status.SaleNotOwnedByPurchaserException;
import segundum.domain.exceptions.sale.status.SaleNotOwnedBySellerException;
import segundum.domain.exceptions.sale.status.SaleNotReservedException;

/**
 * Represents a sale aggregate in the system.
 */
public class Sale {

	/**
	 * The list of domain events that have not yet been committed.
	 */
    private final List<DomainEvent> uncommittedEvents = new ArrayList<>();

	/**
	 * The unique identifier of the sale.
	 */
    private SaleId saleId;
	/**
	 * The product identifier of the sale.
	 */
    private ProductId productId;
	/**
	 * The seller identifier of the sale.
	 */
    private SellerId sellerId;
	/**
	 * The seller name of the sale.
	 */
    private SellerName sellerName;
	/**
	 * The seller surname of the sale.
	 */
    private SellerSurname sellerSurname;
	/**
	 * The purchaser identifier of the sale.
	 */
    private PurchaserId purchaserId;
	/**
	 * The purchaser name of the sale.
	 */
    private PurchaserName purchaserName;
	/**
	 * The purchaser surname of the sale.
	 */
    private PurchaserSurname purchaserSurname;
	/**
	 * The price of the sale.
	 */
    private Price price;
	/**
	 * The title of the sale.
	 */
    private Title title;
	/**
	 * The pickup location of the sale.
	 */
    private PickupLocation pickupLocation;
	/**
	 * The date time of the sale.
	 */
    private DateTime datetime;
	/**
	 * The order status of the sale.
	 */
    private OrderStatus orderStatus;

	/**
	 * Constructs a new empty Sale object.
	 */
    Sale() {
    }

	/**
	 * Constructs a new Sale object from the given proposal event.
	 *
	 * @param event the sale proposed event
	 * @throws PurchaserCannotBeSellerException if the purchaser is the seller
	 */
    Sale(SaleProposed event) {
        ensurePurchaserIsNotSeller(event.getSellerId(), event.getPurchaserId());
        apply(event);
    }

	/**
	 * Reserves the sale for the given seller.
	 *
	 * @param sellerId the seller identifier
	 * @throws SaleNotPendingException if the sale is not pending
	 * @throws SaleNotOwnedBySellerException if the sale is not owned by the given seller
	 */
    public void reserve(SellerId sellerId) {
        ensureIsPending();
        ensureIsSeller(sellerId);
        apply(new SaleReserved(saleId, productId));
    }

	/**
	 * Rejects the sale for the given seller.
	 *
	 * @param sellerId the seller identifier
	 * @throws SaleNotPendingException if the sale is not pending
	 * @throws SaleNotOwnedBySellerException if the sale is not owned by the given seller
	 */
    public void reject(SellerId sellerId) {
        ensureIsPending();
        ensureIsSeller(sellerId);
        apply(new SaleRejected(saleId, productId));
    }

	/**
	 * Completes the sale for the given seller.
	 *
	 * @param sellerId the seller identifier
	 * @throws SaleNotReservedException if the sale is not reserved
	 * @throws SaleNotOwnedBySellerException if the sale is not owned by the given seller
	 */
    public void complete(SellerId sellerId) {
        ensureIsReserved();
        ensureIsSeller(sellerId);
        apply(new SaleCompleted(saleId, productId, sellerId, purchaserId));
    }

	/**
	 * Cancels the sale by the given seller.
	 *
	 * @param sellerId the seller identifier
	 * @throws SaleNotReservedException if the sale is not reserved
	 * @throws SaleNotOwnedBySellerException if the sale is not owned by the given seller
	 */
    public void cancelBySeller(SellerId sellerId) {
        ensureIsReserved();
        ensureIsSeller(sellerId);
        apply(new SaleCancelledBySeller(saleId, productId));
    }

	/**
	 * Cancels the sale by the given purchaser.
	 *
	 * @param purchaserId the purchaser identifier
	 * @throws SaleNotReservedException if the sale is not reserved
	 * @throws SaleNotOwnedByPurchaserException if the sale is not owned by the given purchaser
	 */
    public void cancelByPurchaser(PurchaserId purchaserId) {
        ensureIsReserved();
        ensureIsPurchaser(purchaserId);
        apply(new SaleCancelledByPurchaser(saleId, productId));
    }

	/**
	 * Applies the given proposal event to the sale and registers it as uncommitted.
	 *
	 * @param event the sale proposed event
	 */
    private void apply(SaleProposed event) {
        when(event);
        uncommittedEvents.add(event);
    }

	/**
	 * Applies the given reserved event to the sale and registers it as uncommitted.
	 *
	 * @param event the sale reserved event
	 */
    private void apply(SaleReserved event) {
        when(event);
        uncommittedEvents.add(event);
    }

	/**
	 * Applies the given rejected event to the sale and registers it as uncommitted.
	 *
	 * @param event the sale rejected event
	 */
    private void apply(SaleRejected event) {
        when(event);
        uncommittedEvents.add(event);
    }

	/**
	 * Applies the given completed event to the sale and registers it as uncommitted.
	 *
	 * @param event the sale completed event
	 */
    private void apply(SaleCompleted event) {
        when(event);
        uncommittedEvents.add(event);
    }

	/**
	 * Applies the given cancelled-by-seller event to the sale and registers it as uncommitted.
	 *
	 * @param event the sale cancelled by seller event
	 */
    private void apply(SaleCancelledBySeller event) {
        when(event);
        uncommittedEvents.add(event);
    }

	/**
	 * Applies the given cancelled-by-purchaser event to the sale and registers it as uncommitted.
	 *
	 * @param event the sale cancelled by purchaser event
	 */
    private void apply(SaleCancelledByPurchaser event) {
        when(event);
        uncommittedEvents.add(event);
    }

	/**
	 * Dispatches the given domain event to the sale.
	 *
	 * @param event the domain event to dispatch
	 */
    void when(DomainEvent event) {
        ((SaleEvent) event).dispatch(this);
    }

	/**
	 * Rebuilds the sale state from the given proposal event.
	 *
	 * @param event the sale proposed event
	 */
    public void when(SaleProposed event) {
        this.saleId = event.getSaleId();
        this.productId = event.getProductId();
        this.sellerId = event.getSellerId();
        this.sellerName = event.getSellerName();
        this.sellerSurname = event.getSellerSurname();
        this.purchaserId = event.getPurchaserId();
        this.purchaserName = event.getPurchaserName();
        this.purchaserSurname = event.getPurchaserSurname();
        this.price = event.getPrice();
        this.title = event.getTitle();
        this.pickupLocation = event.getPickupLocation();
        this.datetime = event.getDatetime();
        this.orderStatus = OrderStatus.PENDING;
    }

	/**
	 * Sets the order status to reserved from the given reserved event.
	 *
	 * @param event the sale reserved event
	 */
    public void when(SaleReserved event) {
        this.orderStatus = OrderStatus.RESERVED;
    }

	/**
	 * Sets the order status to rejected from the given rejected event.
	 *
	 * @param event the sale rejected event
	 */
    public void when(SaleRejected event) {
        this.orderStatus = OrderStatus.REJECTED;
    }

	/**
	 * Sets the order status to completed from the given completed event.
	 *
	 * @param event the sale completed event
	 */
    public void when(SaleCompleted event) {
        this.orderStatus = OrderStatus.COMPLETED;
    }

	/**
	 * Sets the order status to cancelled from the given cancelled-by-seller event.
	 *
	 * @param event the sale cancelled by seller event
	 */
    public void when(SaleCancelledBySeller event) {
        this.orderStatus = OrderStatus.CANCELLED;
    }

	/**
	 * Sets the order status to cancelled from the given cancelled-by-purchaser event.
	 *
	 * @param event the sale cancelled by purchaser event
	 */
    public void when(SaleCancelledByPurchaser event) {
        this.orderStatus = OrderStatus.CANCELLED;
    }

	/**
	 * Returns the list of uncommitted domain events and clears the internal list.
	 *
	 * @return an immutable list of uncommitted domain events
	 */
    public List<DomainEvent> getUncommittedEvents() {
        List<DomainEvent> events = List.copyOf(uncommittedEvents);
        uncommittedEvents.clear();
        return events;
    }

	/**
	 * Returns the sale identifier.
	 *
	 * @return the sale identifier
	 */
    public SaleId getSaleId() {
        return saleId;
    }

	/**
	 * Returns the product identifier of the sale.
	 *
	 * @return the product identifier of the sale
	 */
    public ProductId getProductId() {
        return productId;
    }

	/**
	 * Returns the seller identifier of the sale.
	 *
	 * @return the seller identifier of the sale
	 */
    public SellerId getSellerId() {
        return sellerId;
    }

	/**
	 * Returns the seller name of the sale.
	 *
	 * @return the seller name of the sale
	 */
    public SellerName getSellerName() {
        return sellerName;
    }

	/**
	 * Returns the seller surname of the sale.
	 *
	 * @return the seller surname of the sale
	 */
    public SellerSurname getSellerSurname() {
        return sellerSurname;
    }

	/**
	 * Returns the purchaser identifier of the sale.
	 *
	 * @return the purchaser identifier of the sale
	 */
    public PurchaserId getPurchaserId() {
        return purchaserId;
    }

	/**
	 * Returns the purchaser name of the sale.
	 *
	 * @return the purchaser name of the sale
	 */
    public PurchaserName getPurchaserName() {
        return purchaserName;
    }

	/**
	 * Returns the purchaser surname of the sale.
	 *
	 * @return the purchaser surname of the sale
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
	 * Returns the date time of the sale.
	 *
	 * @return the date time of the sale
	 */
    public DateTime getDatetime() {
        return datetime;
    }

	/**
	 * Returns the order status of the sale.
	 *
	 * @return the order status of the sale
	 */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

	/**
	 * Ensures that the given seller owns the sale.
	 *
	 * @param sellerId the seller identifier to check
	 * @throws SaleNotOwnedBySellerException if the sale is not owned by the given seller
	 */
    private void ensureIsSeller(SellerId sellerId) {
        if (!this.sellerId.getValue().equals(sellerId.getValue())) {
            throw new SaleNotOwnedBySellerException();
        }
    }

	/**
	 * Ensures that the given purchaser owns the sale.
	 *
	 * @param purchaserId the purchaser identifier to check
	 * @throws SaleNotOwnedByPurchaserException if the sale is not owned by the given purchaser
	 */
    private void ensureIsPurchaser(PurchaserId purchaserId) {
        if (!this.purchaserId.getValue().equals(purchaserId.getValue())) {
            throw new SaleNotOwnedByPurchaserException();
        }
    }

	/**
	 * Ensures that the purchaser is not the same as the seller.
	 *
	 * @param sellerId the seller identifier
	 * @param purchaserId the purchaser identifier
	 * @throws PurchaserCannotBeSellerException if the seller and purchaser are the same
	 */
    private void ensurePurchaserIsNotSeller(SellerId sellerId, PurchaserId purchaserId) {
        if (sellerId.getValue().equals(purchaserId.getValue())) {
            throw new PurchaserCannotBeSellerException();
        }
    }

	/**
	 * Ensures that the sale is pending.
	 *
	 * @throws SaleNotPendingException if the sale is not pending
	 */
    private void ensureIsPending() {
        if (orderStatus != OrderStatus.PENDING) {
            throw new SaleNotPendingException();
        }
    }

	/**
	 * Ensures that the sale is reserved.
	 *
	 * @throws SaleNotReservedException if the sale is not reserved
	 */
    private void ensureIsReserved() {
        if (orderStatus != OrderStatus.RESERVED) {
            throw new SaleNotReservedException();
        }
    }

}
