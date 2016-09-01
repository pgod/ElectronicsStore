package godziszewski.patryk.ElectronicsStore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/cart")
public class CartController {
	@RequestMapping
	public String get(HttpServletRequest request,HttpServletResponse response,
			@CookieValue(name = "cartId", required = false) String cookieValue)
	{
		if(cookieValue == null)
		{
			cookieValue = request.getSession().getId();
		}
		return "redirect:/cart/"+cookieValue;
	}
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public String getCart(Model model,@PathVariable String cartId)
	{
		model.addAttribute("cartId", cartId);
		return "cart";
	}
}
