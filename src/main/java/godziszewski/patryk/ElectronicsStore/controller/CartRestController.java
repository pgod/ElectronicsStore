package godziszewski.patryk.ElectronicsStore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import godziszewski.patryk.ElectronicsStore.domain.Cart;
import godziszewski.patryk.ElectronicsStore.domain.CartItem;
import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.exception.ProductNotFoundException;
import godziszewski.patryk.ElectronicsStore.service.CartService;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

@Controller
@RequestMapping(value = "rest/cart")
public class CartRestController {
	@Autowired 
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart cart)
	{
		return cartService.create(cart);
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId)
	{
		return cartService.read(cartId);
	}
	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId,
			@RequestBody Cart cart)
	{
		cartService.update(cartId, cart);
	}
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String cartId)
	{
		cartService.delete(cartId);
	}
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable Integer productId, HttpServletRequest request)
	{
		String sessionId = request.getSession().getId();
		Cart cart = cartService.read(sessionId);
		if(cart== null) 
		{
		cart = cartService.create(new Cart(sessionId));
		}
		Product product = productService.getProductById(productId);
		if(product == null) 
		{
		throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		cart.addCartItem(new CartItem(product));
		cartService.update(sessionId, cart);
	}
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable Integer productId, HttpServletRequest request)
	{
		String sessionId = request.getSession().getId();
		Cart cart = cartService.read(sessionId);
		if(cart== null) 
		{
		cart = cartService.create(new Cart(sessionId));
		}
		Product product = productService.getProductById(productId);
		if(product == null) 
		{
		throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		cart.removeCartItem(new CartItem(product));
		cartService.update(sessionId, cart);
	}
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Incorrect request, check request data")
	public void handleClientErrors(Exception ex) { }
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
	public void handleServerErrors(Exception ex) { }
}
