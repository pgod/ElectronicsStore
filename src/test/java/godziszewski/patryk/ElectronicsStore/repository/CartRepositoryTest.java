package godziszewski.patryk.ElectronicsStore.repository;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import godziszewski.patryk.ElectronicsStore.config.RootConfig;
import godziszewski.patryk.ElectronicsStore.dao.CartDao;
import godziszewski.patryk.ElectronicsStore.model.Cart;
import godziszewski.patryk.ElectronicsStore.model.CartItem;
import godziszewski.patryk.ElectronicsStore.model.Product;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class CartRepositoryTest {
	@Autowired CartDao cartDao;
	
	@Test
	public void createCorrectCartTest()
	{
		Cart cart = new Cart("1");
		cart.addCartItem(new CartItem(new Product(1, "iphone", new BigDecimal(1500))));
		
		Cart createdCart = cartDao.create(cart);
		
		cartDao.delete("1");
		Assert.assertNotNull(createdCart);
		Assert.assertEquals(cart, createdCart);
	}
	@Test
	public void createCartThatAlreadyExistsTest()
	{
		Cart oldCart = new Cart("1");
		Cart newCart = new Cart("1");
		Exception caughtException = null;
		
		cartDao.create(oldCart);
		try{
			cartDao.create(newCart);
		}catch(Exception ex)
		{
			caughtException=ex;
		}
		cartDao.delete("1");
		Assert.assertNotNull(caughtException);
		
	}
	@Test
	public void readCorrectCartTest()
	{
		Cart cart = new Cart("1");
		
		cartDao.create(cart);
		
		Cart readCart = cartDao.read(cart.getCartId());
		cartDao.delete("1");
		
		Assert.assertNotNull(readCart);
		Assert.assertEquals(cart, readCart);
		
	}
	@Test
	public void readIncorrectCartTest()
	{
		Cart foundCart = cartDao.read("wrongId");
		
		Assert.assertNull(foundCart);
	}
	@Test
	public void readNullCartTest()
	{
		Cart foundCart = cartDao.read(null);
		
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
		
		cartDao.create(cart);
		cartDao.update(cart.getCartId(), changedCart);
		
		Cart readCart = cartDao.read(cart.getCartId());
		
		cartDao.delete("1");
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
			cartDao.update("1", cart);
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
		cartDao.create(cart);
		
		cartDao.delete(cart.getCartId());
		Cart readCart = cartDao.read(cart.getCartId());
		
		Assert.assertNull(readCart);
		
	}
	@Test
	public void deleteIncorrectCartTest()
	{
		Exception exception = null;
		try{
			cartDao.delete("incorrectCartId");
		} catch(Exception ex)
		{
			exception=ex;
		}
		Assert.assertNotNull(exception);
	}
}
