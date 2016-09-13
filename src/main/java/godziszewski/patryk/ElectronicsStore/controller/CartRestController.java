package godziszewski.patryk.ElectronicsStore.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import godziszewski.patryk.ElectronicsStore.exception.ProductNotFoundException;
import godziszewski.patryk.ElectronicsStore.model.Cart;
import godziszewski.patryk.ElectronicsStore.model.CartItem;
import godziszewski.patryk.ElectronicsStore.model.Product;
import godziszewski.patryk.ElectronicsStore.service.CartService;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

@RestController
@RequestMapping(value = "rest/cart")
public class CartRestController {
	@Autowired 
	private CartService cartService;
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST)
	public  Cart create(Cart cart)
	{
		return cartService.create(cart);
	}
	
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public  Cart read(@PathVariable(value = "cartId") String cartId)
	{
		return cartService.read(cartId);
	}
	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId,
			Cart cart)
	{
		cartService.update(cartId, cart);
	}
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String cartId,
			HttpServletRequest request, HttpServletResponse response)
	{
		cartService.delete(cartId);
		deleteCartCookie(request, response);
	}
	@RequestMapping(value = "/add/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable Integer productId, HttpServletRequest request,
			@CookieValue(name = "cartId", required = false) String cookieValue,
			HttpServletResponse response)
	{
		Cart cart = cartService.read(cookieValue);
		if(cart == null) 
		{
			cookieValue = this.createCartCookie(2419200, request, response); //4 weeks
			cart = cartService.create(new Cart(cookieValue));
		}
		Product product = productService.getProductById(productId);
		if(product == null) 
		{
		throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		cart.addCartItem(new CartItem(product));
		cartService.update(cookieValue, cart);
	}
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable Integer productId, HttpServletRequest request,
			@CookieValue(name = "cartId") String cookieValue)
	{
		Cart cart = cartService.read(cookieValue);
		if(cart == null) 
		{
		cart = cartService.create(new Cart(cookieValue));
		}
		Product product = productService.getProductById(productId);
		if(product == null) 
		{
		throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		cart.removeCartItem(new CartItem(product));
		cartService.update(cookieValue, cart);
	}
	private String createCartCookie(int maxAge,HttpServletRequest request, HttpServletResponse response)
	{
		String sessionId= request.getSession().getId();
		Cookie cookie = new Cookie("cartId", sessionId);
		cookie.setPath(request.getServletContext().getContextPath()+"/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
		return sessionId;
	}
	private void deleteCartCookie(HttpServletRequest request, HttpServletResponse response)
	{
		Cookie cookie = new Cookie("cartId", null);  // Not necessary, but saves bandwidth.
		cookie.setPath(request.getServletContext().getContextPath()+"/");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="Incorrect request, check request data")
	public void handleClientErrors(Exception ex) { }
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Internal server error")
	public void handleServerErrors(Exception ex) { }
}
