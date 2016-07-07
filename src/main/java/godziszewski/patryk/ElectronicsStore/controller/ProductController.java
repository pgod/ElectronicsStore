package godziszewski.patryk.ElectronicsStore.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import godziszewski.patryk.ElectronicsStore.service.ProductService;

@RequestMapping("/products")
@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String list(Model model)
	{
		model.addAttribute("products",productService.getAllProducts());
		return "products";
	}
	@RequestMapping("/all")
	public String allProducts(Model model)
	{
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category")
	String productCategory)
	{
		model.addAttribute("products", productService.getProductsByCategory(productCategory));
		return "products";
	}
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(Model model, @MatrixVariable(pathVar="ByCriteria")
	Map <String, List<String>> filterParams)
	{
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	@RequestMapping("/product")
	public String getProductById(Model model, @RequestParam("id") String productId)
	{
		model.addAttribute("product", productService.getProductById(productId));
		return "product";
	}
}