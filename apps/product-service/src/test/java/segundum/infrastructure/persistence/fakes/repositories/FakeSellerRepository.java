package segundum.infrastructure.persistence.fakes.repositories;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import segundum.domain.models.seller.Email;
import segundum.domain.models.seller.Seller;
import segundum.domain.models.seller.SellerId;
import segundum.domain.repositories.SellerRepository;

public class FakeSellerRepository implements SellerRepository {

	private final Map<SellerId, Seller> sellers = new HashMap<>();

	@Override
	public Optional<Seller> findById(SellerId id) {
		return Optional.ofNullable(sellers.get(id));
	}

	@Override
	public boolean existsById(SellerId id) {
		return sellers.containsKey(id);
	}

	@Override
	public Seller create(Seller seller) {
		sellers.put(seller.getSellerId(), seller);
		return seller;
	}

	@Override
	public Seller update(Seller seller) {
		sellers.put(seller.getSellerId(), seller);
		return seller;
	}

	@Override
	public boolean existsByEmail(Email email) {
		return sellers.values().stream()
				.anyMatch(s -> s.getEmail().getValue().equals(email.getValue()));
	}
}
