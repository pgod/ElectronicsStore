package godziszewski.patryk.ElectronicsStore.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.dao.CartRepository;
import godziszewski.patryk.ElectronicsStore.model.Cart;

@Repository
public class InMemoryCartRepository implements CartRepository {
	private Map <String, Cart> listOfCarts;
	
	public InMemoryCartRepository() {
		listOfCarts = new HashMap<>();
	}

	public Cart create(Cart cart) {
		if(listOfCarts.containsKey(cart.getCartId()))
		{
			throw new IllegalArgumentException(String.format("Can not create cart"
					+ "Cart with specified id (%) already exists.",cart.getCartId()));
		}
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}

	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	public void update(String cartId, Cart cart) {
		if(!listOfCarts.containsKey(cartId))
		{
			throw new IllegalArgumentException(String.format("Can not update cart. "
					+ "Cart with specified id (%) does not exists.",cartId));
		}
		listOfCarts.put(cartId, cart);
	}

	public void delete(String cartId) {
		if(!listOfCarts.containsKey(cartId))
		{
			throw new IllegalArgumentException(String.format("Can not update cart. "
					+ "Cart with specified id (%) does not exists.",cartId));
		}
		listOfCarts.remove(cartId);
	}
}
