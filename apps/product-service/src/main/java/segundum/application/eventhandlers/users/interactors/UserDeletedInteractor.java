package segundum.application.eventhandlers.users.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.users.UserDeletedHandler;
import segundum.application.events.users.UserDeleted;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.SellerRepository;

/**
 * Interactor for handling user deleted events.
 * Deletes the Seller replica in the product service when a user is deleted.
 */
public class UserDeletedInteractor implements UserDeletedHandler {

	/**
	 * The repository for managing seller data.
	 */
	private final SellerRepository sellerRepository;
	
	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new UserDeletedHandlerInteractor with the given dependencies.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logger the logger
	 */
	public UserDeletedInteractor(SellerRepository sellerRepository, LogEmitter logEmitter) {
		this.sellerRepository = sellerRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void handle(UserDeleted event) {
		try {
			SellerId sellerId = SellerId.fromUUID(event.getUserId());
			Optional<Seller> seller = sellerRepository.findById(sellerId);
			if (seller.isEmpty()) {
				logEmitter.warn("Seller with ID " + sellerId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Seller sellerEntity = seller.get();
			sellerEntity.delete();
			sellerRepository.delete(sellerId);
			sellerRepository.update(sellerEntity);
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}
