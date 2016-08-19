package godziszewski.patryk.ElectronicsStore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import godziszewski.patryk.ElectronicsStore.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@RequestMapping("/order/1234/2")
	public String process()
	{
		orderService.processOrder(1234, 2);
		return "redirect:/products";
	}
}
