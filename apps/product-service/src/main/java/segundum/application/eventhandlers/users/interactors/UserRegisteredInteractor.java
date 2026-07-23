package segundum.application.eventhandlers.users.interactors;

import segundum.application.eventhandlers.users.UserRegisteredHandler;
import segundum.application.events.users.UserRegistered;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.domain.outbound.LogEmitter;
import segundum.domain.repositories.SellerRepository;

/**
 * Interactor for handling user registered events.
 * Creates a new Seller replica in the product service when a user is registered.
 */
public class UserRegisteredInteractor implements UserRegisteredHandler {

	/**
	 * The repository for managing seller data.
	 */
	private final SellerRepository sellerRepository;
	
	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new UserRegisteredHandlerInteractor with the given dependencies.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logger the logger
	 */
	public UserRegisteredInteractor(SellerRepository sellerRepository, LogEmitter logEmitter) {
		this.sellerRepository = sellerRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void handle(UserRegistered event) {
		try {
			SellerId sellerId = SellerId.fromUUID(event.getUserId());
			if (sellerRepository.existsById(sellerId)) {
				logEmitter.warn("Seller with ID " + sellerId + " already exists. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Email email = new Email(event.getEmail());
			if(sellerRepository.existsByEmail(email)) {
				logEmitter.warn("Seller with email " + email + " already exists. Event " + event.getClass().getSimpleName() + " discarded.");
				return;
			}
			Name name = new Name(event.getName());
			Surname surname = new Surname(event.getSurname());
			Seller seller = SellerFactory.create(sellerId, name, surname, email);
			sellerRepository.create(seller);
		}
		catch(DomainException e) {
			logEmitter.warn("Event " + event.getClass().getSimpleName() + " discarded: " + e.getMessage());
		}
	}

}
