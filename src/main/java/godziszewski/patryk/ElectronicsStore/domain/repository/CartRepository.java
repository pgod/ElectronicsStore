package godziszewski.patryk.ElectronicsStore.domain.repository;

import godziszewski.patryk.ElectronicsStore.domain.Cart;

public interface CartRepository {
	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
}
