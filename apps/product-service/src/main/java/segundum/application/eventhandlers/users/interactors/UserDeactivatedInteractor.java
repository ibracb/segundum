package segundum.application.eventhandlers.users.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.users.UserDeactivatedHandler;
import segundum.application.events.users.UserDeactivated;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.SellerRepository;

/**
 * Interactor for handling user deactivated events.
 * Deactivates the Seller replica in the product service when a user is deactivated.
 */
public class UserDeactivatedInteractor implements UserDeactivatedHandler {

	/**
	 * The repository for managing seller data.
	 */
	private final SellerRepository sellerRepository;
	
	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new UserDeactivatedHandlerInteractor with the given dependencies.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logger the logger
	 */
	public UserDeactivatedInteractor(SellerRepository sellerRepository, LogEmitter logEmitter) {
		this.sellerRepository = sellerRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void handle(UserDeactivated event) {
		try {
			SellerId sellerId = SellerId.fromUUID(event.getUserId());
			Optional<Seller> seller = sellerRepository.findById(sellerId);
			if (seller.isEmpty()) {
				logEmitter.warn("Seller with ID " + sellerId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Seller sellerEntity = seller.get();
			sellerEntity.deactivate();
			sellerRepository.update(sellerEntity);
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}
