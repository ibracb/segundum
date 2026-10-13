package segundum.infrastructure.persistence.fakes.finders;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import segundum.application.finders.SellerFinder;
import segundum.application.readmodels.seller.SellerReadModel;
import segundum.domain.models.seller.SellerId;

public class FakeSellerFinder implements SellerFinder {

	private final Map<String, SellerReadModel> sellers = new HashMap<>();

	@Override
	public Optional<SellerReadModel> findById(SellerId id) {
		return Optional.ofNullable(sellers.get(id.getValue().toString()));
	}

	public void put(SellerReadModel seller) {
		sellers.put(seller.getSellerId(), seller);
	}

}
