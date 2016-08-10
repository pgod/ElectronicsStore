package godziszewski.patryk.ElectronicsStore.controller;


import java.io.File;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.exception.NoProductsFoundUnderCategoryException;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

@RequestMapping("/products")
@Controller
public class ProductController {
	
	
	private ProductService productService;
	
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	@RequestMapping
	public String list(Model model)
	{
		model.addAttribute("products",productService.getAllProducts());
		return "products";
	}
	@RequestMapping("/{category}")
	public String getProductsByCategory(Model model, @PathVariable("category")
	String productCategory)
	{
		List <Product> products = productService.getProductsByCategory(productCategory);
		if(products == null || products.isEmpty())
		{
			throw new NoProductsFoundUnderCategoryException();
		}
		model.addAttribute("products", products);
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
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model)
	{
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product productToBeAdded,
			BindingResult result, HttpServletRequest request)
	{
		if(result.hasErrors())
		{
			return "addProduct";
		}
		String[] supressedFields = result.getSuppressedFields();
		if(supressedFields.length>0)
		{
			throw new RuntimeException("Trial of binding supressed fields: "
					+StringUtils.arrayToCommaDelimitedString(supressedFields));
		}
		MultipartFile productImage = productToBeAdded.getProductImage();
		String rootdirectory = request.getServletContext().getRealPath("/");
		if(productImage != null && !productImage.isEmpty())
		{
			try {
				FileCopyUtils.copy(productImage.getBytes(), 
						new File( rootdirectory + "resources\\images\\"+productToBeAdded.getProductId()+".png"));
			}  catch (Exception e) {
				throw new RuntimeException("Error occoured while uploading image of the product", e);
			}
		}
		productService.addProduct(productToBeAdded);
		return "redirect:/products";
	}

	
	@InitBinder 
	public void initialiseBinder(WebDataBinder binder)
	{
		binder.setAllowedFields("productId","name","unitPrice","description","manufacturer",
				"category","unitsInStock", "condition","productImage");
	}
}