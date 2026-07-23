package segundum.infrastructure.persistence.jpa.seller;

import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Name;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerFactory;
import segundum.domain.models.seller.SellerId;
import segundum.domain.models.seller.SellerStatus;
import segundum.domain.models.seller.Surname;

/**
 * Mapper between domain Seller and JPA SellerJpaEntity.
 */
public class SellerMapper {
	
	/**
	 * Private constructor to prevent instantiation.
	 */
	private SellerMapper() {
	}

	/**
	 * Converts a domain Seller to a JPA entity.
	 *
	 * @param seller the domain seller
	 * @return the JPA entity
	 */
	public static SellerJpaEntity toEntity(Seller seller) {
		return new SellerJpaEntity(
				seller.getSellerId().getValue().toString(),
				seller.getName().getValue(),
				seller.getSurname().getValue(),
				seller.getEmail().getValue(),
				seller.getStatus().name());
	}

	/**
	 * Converts a JPA entity to a domain Seller.
	 *
	 * @param entity the JPA entity
	 * @return the domain seller
	 */
	public static Seller toDomain(SellerJpaEntity entity) {
		return SellerFactory.reconstitute(
				SellerId.fromString(entity.getId()),
				new Name(entity.getName()),
				new Surname(entity.getSurname()),
				new Email(entity.getEmail()),
				SellerStatus.valueOf(entity.getStatus()));
	}

}
