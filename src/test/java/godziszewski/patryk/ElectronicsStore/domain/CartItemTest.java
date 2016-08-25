package godziszewski.patryk.ElectronicsStore.domain;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import godziszewski.patryk.ElectronicsStore.domain.CartItem;
import godziszewski.patryk.ElectronicsStore.domain.Product;


public class CartItemTest {
	private CartItem cartItem;
	@Before
	public void setup()
	{
		cartItem = new CartItem();
	}
	@Test
	public void cartItem_total_price_should_be_eaual_to_product_unit_price_in_case_of_single_quantity()
	{
		Product iphone = new Product(1234,"iPhone 5s", new BigDecimal(500));
		cartItem.setProduct(iphone);
		
		BigDecimal totalPrice = cartItem.getTotalPrice();
		
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
	}
}
