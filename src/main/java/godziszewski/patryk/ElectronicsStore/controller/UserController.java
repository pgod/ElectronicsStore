package godziszewski.patryk.ElectronicsStore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import godziszewski.patryk.ElectronicsStore.domain.Customer;
import godziszewski.patryk.ElectronicsStore.service.CustomerService;

@Controller
@RequestMapping("/user")
public class UserController {
	private CustomerService customerService;
	
	@Autowired
	public UserController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(value = "/details", method = RequestMethod.GET)
	public String getUserDetails(Model model,
			@AuthenticationPrincipal User activeUser)
	{
		Customer customer = customerService.getCustomerByEmail(
				activeUser.getUsername());
		model.addAttribute("customer", customer);
		return "userDetails";
	}
	@RequestMapping(value = "/details", method = RequestMethod.POST)
	public String processUserDetails(Model model, 
			@AuthenticationPrincipal User activeUser,@ModelAttribute("customer") @Valid Customer customer,
			BindingResult result, HttpServletRequest request)
	{
		if(result.hasErrors())
		{
			return "/userDetails";
		}
		String userEmail = activeUser.getUsername();
		customer.setEmail(userEmail);
		customerService.update(userEmail, customer);
		
		return "redirect:/products";
	}
}
