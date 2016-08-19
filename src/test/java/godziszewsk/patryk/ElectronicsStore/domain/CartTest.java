package godziszewsk.patryk.ElectronicsStore.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import godziszewski.patryk.ElectronicsStore.domain.Cart;
import godziszewski.patryk.ElectronicsStore.domain.CartItem;
import godziszewski.patryk.ElectronicsStore.domain.Product;

public class CartTest {
	private Cart cart;
	
	@Before
	public void setup()
	{
		cart = new Cart();
	}
	
	@Test
	public void cart_grand_total_should_be_eqaual_to_cart_item_price_in_case_of_single_quantity()
	{
		Product iphone = new Product(1234,"iPhone 5s", new BigDecimal(500));
		CartItem cartItem = new CartItem(iphone);
		cart.addCartItem(cartItem);
		
		BigDecimal grandTotal = cart.getGrandTotal();
		
		Assert.assertEquals(cartItem.getTotalPrice(), grandTotal);
	}
}
