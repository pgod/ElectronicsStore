package godziszewski.patryk.ElectronicsStore.exception;

import java.io.Serializable;

public class InvalidCartException extends RuntimeException {
	private static final long serialVersionUID = -238377124985427627L;
	private String cartId;
	public InvalidCartException(String cartId)
	{
		this.cartId = cartId;
	}
	public String getCartId() {
		return cartId;
	}
}
