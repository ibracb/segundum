package segundum.application.eventhandlers.users.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.users.UserUpdatedHandler;
import segundum.application.events.users.UserUpdated;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.SellerRepository;

/**
 * Interactor for handling user updated events.
 * Updates the Seller replica in the product service when a user is updated.
 */
public class UserUpdatedInteractor implements UserUpdatedHandler {

	/**
	 * The repository for managing seller data.
	 */
	private final SellerRepository sellerRepository;
	
	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new UserUpdatedHandlerInteractor with the given dependencies.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logger the logger
	 */
	public UserUpdatedInteractor(SellerRepository sellerRepository, LogEmitter logEmitter) {
		this.sellerRepository = sellerRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void handle(UserUpdated event) {
		try {
			SellerId sellerId = SellerId.fromUUID(event.getUserId());
			Optional<Seller> seller = sellerRepository.findById(sellerId);
			if (seller.isEmpty()) {
				logEmitter.warn("Seller with ID " + sellerId + " not found. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Seller sellerEntity = seller.get();
			Name name = new Name(event.getName());
			Surname surname = new Surname(event.getSurname());
			sellerEntity.changeName(name);
			sellerEntity.changeSurname(surname);
			sellerRepository.update(sellerEntity);
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}
