package godziszewski.patryk.ElectronicsStore.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.view.InternalResourceView;

import godziszewski.patryk.ElectronicsStore.model.Product;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

public class ProductControllerTest {
	@Test
	public void testProductsPage() throws Exception
	{
		ProductService mockService = mock(ProductService.class);
		List<Product> listOfProducts = createProductList(10);
		when(mockService.getAllProducts()) 
		.thenReturn(listOfProducts);
		
		ProductController controller = new ProductController(mockService);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/products.jsp"))
				.build();
		mockMvc.perform(get("/products"))
		.andExpect(view().name("products"))
		.andExpect(model().attributeExists("products"))
		.andExpect(model().attribute("products",
		hasItems(listOfProducts.toArray())));
	}
	@Test
	public void testSearchingByCategory() throws Exception
	{
		ProductService mockService = mock(ProductService.class);
		List<Product> listOfProducts = new ArrayList<Product>();
		
		
		Product dell = new Product(1200,"Dell Inspiron", new BigDecimal("1500"));
		dell.setCategory("laptop");
		listOfProducts.add(dell);
		
		
		when(mockService.getProductsByCategory("laptop")) 
		.thenReturn(listOfProducts);
		
		
		ProductController controller = new ProductController(mockService);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/products.jsp"))
				.build();
		mockMvc.perform(get("/products/laptop"))
		.andExpect(view().name("products"))
		.andExpect(model().attributeExists("products"))
		.andExpect(model().attribute("products",
		hasItems(listOfProducts.toArray())));
		
	}
	@Test
	public void testSearchingByCriteria() throws Exception
	{
		ProductService mockService = mock(ProductService.class);
		List<Product> listOfProducts = new ArrayList<Product>();
		
		
		Product dell = new Product(1200,"Dell Inspiron", new BigDecimal("1500"));
		dell.setCategory("laptop");
		dell.setManufacturer("dell");
		listOfProducts.add(dell);
		
		
		Map <String, List<String>> filterParams = new HashMap<String, List<String>>() ;
		
		List<String> brand = new ArrayList<String>();
		brand.add("dell");
		filterParams.put("brand", brand);
		
		List<String> category = new ArrayList<String>();
		category.add("laptop");
		filterParams.put("category", category);
		
		when(mockService.getProductsByFilter(filterParams)) 
		.thenReturn(listOfProducts);
		
		
		ProductController controller = new ProductController(mockService);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/products.jsp"))
				.setRemoveSemicolonContent(false)
				.build();
		
		mockMvc.perform(get("/products/filter/ByCriteria;brand=dell;category=laptop;"))
		.andExpect(view().name("products"))
		.andExpect(model().attributeExists("products"))
		.andExpect(model().attribute("products",
		hasItems(listOfProducts.toArray())));
		
	}
	@Test
	public void testSearchingById() throws Exception
	{
		ProductService mockService = mock(ProductService.class);
		Product dell = new Product(1234,"Dell Inspiron", new BigDecimal("1500"));
		List<Product> pr = new ArrayList<Product>();
		pr.add(dell);
	
		when(mockService.getProductById(1234))
		.thenReturn(dell);
		
		
		ProductController controller = new ProductController(mockService);
		MockMvc mockMvc = standaloneSetup(controller)
				.setSingleView(new InternalResourceView("/WEB-INF/views/product.jsp"))
				.build();
	
		mockMvc.perform(get("/products/product?id=1234"))
		.andExpect(view().name("product"))
		.andExpect(model().attributeExists("product"))
		.andExpect(model().attribute("product",
		equalTo(dell)));
		
	}
	@Test
	public void testShowNewProductForm() throws Exception {
		ProductService mockService = mock(ProductService.class);
		ProductController controller = new ProductController(mockService);
		
	    MockMvc mockMvc = standaloneSetup(controller).build();
	    mockMvc.perform(get("/products/add"))
	           .andExpect(view().name("addProduct"));
	  }
	 @Test
	 public void testProcessNewProductForm() throws Exception {
		 ProductService mockService = mock(ProductService.class);
		 
		 ProductController controller = new ProductController(mockService);
		 Product product = new Product(null, "Dell Inspiron", new BigDecimal("1500"));
		 product.setCategory("laptop");
		 product.setManufacturer("dell");
		 product.setUnitsInStock(100);
		 product.setDescription("modern design");
	    
		 MockMvc mockMvc = standaloneSetup(controller)
	    		.build();
		 mockMvc.perform(post("/products/add")
	    		.param("name", "Dell Inspiron")
	    		.param("unitPrice", "1500")
	    		.param("category", "laptop")
	    		.param("description", "modern design")
	    		.param("manufacturer", "dell")
	    		.param("unitsInStock", "100"))
	    		.andExpect(redirectedUrl("/products"));
	    
	    verify(mockService, atLeastOnce()).addProduct(product);
	  }

	private List<Product> createProductList(int count) {
		List<Product> products = new ArrayList<Product>();
		for (int i=0; i < count; i++) 
		{
			products.add(new Product());
		}
		return products;
		}
}
