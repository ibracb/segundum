package segundum.application.notificationhandlers.interactors;

import java.util.Optional;

import segundum.application.notificationhandlers.UserNotificationHandler;
import segundum.application.notifications.users.UserDeactivatedNotification;
import segundum.application.notifications.users.UserRegisteredNotification;
import segundum.application.notifications.users.UserUpdatedNotification;
import segundum.domain.exceptions.DomainException;
import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.Surname;
import segundum.application.outbound.LogEmitter;
import segundum.domain.repositories.SellerRepository;

/**
 * Interactor for handling notifications from the users bounded context.
 * Keeps the Seller replica in the product service in sync with the users service.
 */
public class UserNotificationHandlerInteractor implements UserNotificationHandler {

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
	public UserNotificationHandlerInteractor(SellerRepository sellerRepository, LogEmitter logEmitter) {
		this.sellerRepository = sellerRepository;
		this.logEmitter = logEmitter;
	}

	@Override
	public void onUserRegisteredNotification(UserRegisteredNotification event) {
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
		catch(Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

	@Override
	public void onUserUpdatedNotification(UserUpdatedNotification event) {
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
		catch(Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

	@Override
	public void onUserDeactivatedNotification(UserDeactivatedNotification event) {
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
		catch(Exception e) {
			logEmitter.error("Unexpected error processing " + event.getClass().getSimpleName() + ": " + e.getMessage(), e);
		}
	}

}
