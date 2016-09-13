package godziszewski.patryk.ElectronicsStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import godziszewski.patryk.ElectronicsStore.dao.CartDao;
import godziszewski.patryk.ElectronicsStore.exception.InvalidCartException;
import godziszewski.patryk.ElectronicsStore.model.Cart;
import godziszewski.patryk.ElectronicsStore.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	@Autowired
	CartDao cartDao;
	
	public Cart create(Cart cart) {
		return cartDao.create(cart);
	}

	public Cart read(String cartId) {
		return cartDao.read(cartId);
	}

	public void update(String cartId, Cart cart) {
		cartDao.update(cartId, cart);
	}

	public void delete(String cartId) {
		cartDao.delete(cartId);
	}

	public Cart validate(String cartId) {
		Cart cart = cartDao.read(cartId);
		if(cart==null || cart.getCartItems().size()==0) {
		throw new InvalidCartException(cartId);
		}
		return cart;
	}
}
