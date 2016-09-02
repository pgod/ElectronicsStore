package godziszewski.patryk.ElectronicsStore.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login()
	{
		return "login";
	}
	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String loginError(Model model)
	{
		model.addAttribute("error", "true");
		return "login";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null)
		{    
			new SecurityContextLogoutHandler().logout(request, response, auth);
			Cookie cookie = new Cookie("remember-me", null);
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		return "redirect:/products";
	}
}
