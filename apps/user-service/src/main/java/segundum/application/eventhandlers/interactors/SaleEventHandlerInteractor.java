package segundum.application.eventhandlers.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.SaleEventHandler;
import segundum.application.events.sales.SaleCompleted;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.user.User;
import segundum.domain.models.user.UserId;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.UserRepository;

/**
 * Interactor for handling events from the sales bounded context.
 * <p>
 * When a sale is completed, increments the purchases of the purchaser
 * and the sales of the seller.
 * </p>
 */
public class SaleEventHandlerInteractor implements SaleEventHandler {

	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * The repository for managing users.
	 */
	private final UserRepository userRepository;

	/**
	 * Constructs a new SaleEventHandlerInteractor with the given dependencies.
	 *
	 * @param userRepository the repository for managing users
	 * @param logEmitter the log emitter
	 */
	public SaleEventHandlerInteractor(UserRepository userRepository, LogEmitter logEmitter) {
		this.userRepository = userRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void onSaleCompleted(SaleCompleted event) {
		incrementSales(event);
		incrementPurchases(event);
	}

	/**
	 * Increments the number of purchases of the purchaser user.
	 *
	 * @param event the sale completed event
	 */
	private void incrementPurchases(SaleCompleted event) {
		try {
			UserId purchaserId = UserId.fromString(event.getPurchaserId().toString());
			Optional<User> purchaser = userRepository.findById(purchaserId);
			if (purchaser.isEmpty()) {
				logEmitter.warn("User with ID " + event.getPurchaserId() + " not found. Event "
						+ event.getClass().getSimpleName() + " discarded.");
				return;
			}
			User purchaserUser = purchaser.get();
			purchaserUser.incrementPurchases();
			userRepository.update(purchaserUser);
		}
		catch (DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

	/**
	 * Increments the number of sales of the seller user.
	 *
	 * @param event the sale completed event
	 */
	private void incrementSales(SaleCompleted event) {
		try {
			UserId sellerId = UserId.fromString(event.getSellerId().toString());
			Optional<User> seller = userRepository.findById(sellerId);
			if (seller.isEmpty()) {
				logEmitter.warn("User with ID " + event.getSellerId() + " not found. Event "
						+ event.getClass().getSimpleName() + " discarded.");
				return;
			}
			User sellerUser = seller.get();
			sellerUser.incrementSales();
			userRepository.update(sellerUser);
		}
		catch (DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}
