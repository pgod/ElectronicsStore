package godziszewski.patryk.ElectronicsStore.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import godziszewski.patryk.ElectronicsStore.config.RootConfig;
import godziszewski.patryk.ElectronicsStore.dao.CartRepository;
import godziszewski.patryk.ElectronicsStore.model.Cart;
import godziszewski.patryk.ElectronicsStore.model.CartItem;
import godziszewski.patryk.ElectronicsStore.model.Product;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class CartRepositoryTest {
	@Autowired CartRepository cartRepository;
	
	@Test
	public void createCorrectCartTest()
	{
		Cart cart = new Cart("1");
		cart.addCartItem(new CartItem(new Product(1, "iphone", new BigDecimal(1500))));
		
		Cart createdCart = cartRepository.create(cart);
		
		cartRepository.delete("1");
		Assert.assertNotNull(createdCart);
		Assert.assertEquals(cart, createdCart);
	}
	@Test
	public void createCartThatAlreadyExistsTest()
	{
		Cart oldCart = new Cart("1");
		Cart newCart = new Cart("1");
		Exception caughtException = null;
		
		cartRepository.create(oldCart);
		try{
			cartRepository.create(newCart);
		}catch(Exception ex)
		{
			caughtException=ex;
		}
		cartRepository.delete("1");
		Assert.assertNotNull(caughtException);
		
	}
	@Test
	public void readCorrectCartTest()
	{
		Cart cart = new Cart("1");
		
		cartRepository.create(cart);
		
		Cart readCart = cartRepository.read(cart.getCartId());
		cartRepository.delete("1");
		
		Assert.assertNotNull(readCart);
		Assert.assertEquals(cart, readCart);
		
	}
	@Test
	public void readIncorrectCartTest()
	{
		Cart foundCart = cartRepository.read("wrongId");
		
		Assert.assertNull(foundCart);
	}
	@Test
	public void readNullCartTest()
	{
		Cart foundCart = cartRepository.read(null);
		
		Assert.assertNull(foundCart);
	}
	@Test
	public void updateCorrectCartTest()
	{
		Cart cart = new Cart("1");
		Product iphone = new Product(1, "iPhone", new BigDecimal(1500));
		
		cart.addCartItem(new CartItem(iphone));
		Cart changedCart = new Cart("1");
		Product dell = new Product(2, "inspiron", new BigDecimal(2000));
		changedCart.addCartItem(new CartItem(dell));
		
		cartRepository.create(cart);
		cartRepository.update(cart.getCartId(), changedCart);
		
		Cart readCart = cartRepository.read(cart.getCartId());
		
		cartRepository.delete("1");
		Assert.assertEquals(changedCart, readCart);
		Assert.assertEquals(dell, readCart.getCartItems().get(2).getProduct());
	}
	@Test
	public void updateIncorrectCartTest()
	{
		Cart cart = new Cart("1");
		Product iphone = new Product(1, "iPhone", new BigDecimal(1500));
		Exception caughtException = null;
		cart.addCartItem(new CartItem(iphone));
		try{
			cartRepository.update("1", cart);
		} catch (Exception ex)
		{
			caughtException = ex;
		}
		Assert.assertNotNull(caughtException);

	}
	@Test
	public void deleteCorrectCartTest()
	{
		Cart cart = new Cart("1");
		Product iphone = new Product(1, "iPhone", new BigDecimal(1500));
		cart.addCartItem(new CartItem(iphone));
		cartRepository.create(cart);
		
		cartRepository.delete(cart.getCartId());
		Cart readCart = cartRepository.read(cart.getCartId());
		
		Assert.assertNull(readCart);
		
	}
	@Test
	public void deleteIncorrectCartTest()
	{
		Exception exception = null;
		try{
			cartRepository.delete("incorrectCartId");
		} catch(Exception ex)
		{
			exception=ex;
		}
		Assert.assertNotNull(exception);
	}
}
