package godziszewski.patryk.ElectronicsStore.service;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import godziszewski.patryk.ElectronicsStore.config.RootConfig;
import godziszewski.patryk.ElectronicsStore.domain.Cart;
import godziszewski.patryk.ElectronicsStore.domain.CartItem;
import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.CartRepository;
import godziszewski.patryk.ElectronicsStore.exception.InvalidCartException;

@ActiveProfiles("CartService-test")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class CartServiceTest {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private CartRepository cartRepository;
	@Test
	public void createCartTest()
	{
		Cart cart = new Cart("1");
		
		when(cartRepository.create(cart))
		.thenReturn(cart);
		Cart createdCart=cartService.create(cart);
		
		Assert.assertNotNull(createdCart);
		Assert.assertThat(createdCart, instanceOf(Cart.class));
	}
	@Test
	public void readCartTest()
	{
		Cart cart = new Cart("1");
		
		when(cartRepository.read(cart.getCartId()))
		.thenReturn(cart);
		
		Assert.assertNotNull(cartService.read(cart.getCartId()));
		Assert.assertEquals(cart, cartService.read(cart.getCartId()));
	}
	@Test
	public void updateCartTest()
	{
		Cart cart = new Cart("1");
		
		cartService.update(cart.getCartId(), cart);
		
		verify(cartRepository,times(1)).update(cart.getCartId(),cart);
	}
	@Test
	public void deleteCartTest()
	{
		cartService.delete("1");
		
		verify(cartRepository,times(1)).delete("1");
	}
	@Test
	public void validateCorrectCartTest()
	{
		Cart cart = new Cart("1");
		Product product = new Product(1, "iPhone", new BigDecimal(1500));
		cart.addCartItem(new CartItem(product));
		cartService.create(cart);
		
		when(cartRepository.read(cart.getCartId()))
		.thenReturn(cart);
		Cart validatedCart = cartService.validate(cart.getCartId());
		
		Assert.assertNotNull(validatedCart);
		Assert.assertEquals(cart, validatedCart);
	}
	@Test
	public void validateIncorrectCartTest()
	{
		InvalidCartException cartException=null;
		Cart cart = new Cart("1");
		cartService.create(cart);
		
		when(cartRepository.read(cart.getCartId()))
		.thenReturn(null);
		try{
			cartService.validate(cart.getCartId());
		} catch(InvalidCartException exception)
		{
			cartException=exception;
		}
		
		Assert.assertNotNull(cartException);
	}
}
