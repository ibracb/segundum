package segundum.application.eventhandlers.interactors;

import java.util.Optional;

import segundum.application.eventhandlers.UserEventHandler;
import segundum.application.events.users.UserDeactivated;
import segundum.application.events.users.UserRegistered;
import segundum.application.events.users.UserUpdated;
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
 * Interactor for handling events from the users bounded context.
 * Keeps the Seller replica in the product service in sync with the users service.
 */
public class UserEventHandlerInteractor implements UserEventHandler {

	/**
	 * The repository for managing seller data.
	 */
	private final SellerRepository sellerRepository;
	
	/**
	 * The logger.
	 */
	private final LogEmitter logEmitter;

	/**
	 * Constructs a new UsersHandlerInteractor with the given dependencies.
	 *
	 * @param sellerRepository the repository for managing seller data
	 * @param logEmitter the logger
	 */
	public UserEventHandlerInteractor(SellerRepository sellerRepository, LogEmitter logEmitter) {
		this.sellerRepository = sellerRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void onUserRegistered(UserRegistered event) {
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

	@Override
	public void onUserUpdated(UserUpdated event) {
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

	@Override
	public void onUserDeactivated(UserDeactivated event) {
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
