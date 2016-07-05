package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;



@Repository
public class InMemoryProductRepository implements ProductRepository{
	
	private List <Product> listOfProducts = new ArrayList<Product>();
	public InMemoryProductRepository()
	{
		Product iphone=new Product("P1234","iPhone 5S", new BigDecimal(500));
		iphone.setDescription("Apple iPhone 5S, smartphone with "
				+ "4 inch screen");
		iphone.setCategory("Smart Phone");
		iphone.setManufacturer("Apple");
		iphone.setUnitsInStock(1000);
		
		Product laptop_dell = new Product("P1235","Dell Inspiron",new BigDecimal(600));
		laptop_dell.setDescription("Dell Inspiron with 14 inch display and Intel core i3 CPU");
		laptop_dell.setCategory("Laptop");
		laptop_dell.setManufacturer("Dell");
		laptop_dell.setUnitsInStock(500);
		
		Product tablet=new Product("P1236","Nexus 7", new BigDecimal(300));
		tablet.setDescription("Google Nexus 7, tablet with great design and responsivness");
		tablet.setCategory("Tablet");
		tablet.setManufacturer("Google");
		tablet.setUnitsInStock(1000);
		
		listOfProducts.add(iphone);
		listOfProducts.add(laptop_dell);
		listOfProducts.add(tablet);		
	}
	
	public List<Product> getAllProducts() 
	{
		return listOfProducts;
	}

	public Product getProductById(String productId) {
		Product productById = null;
		for(Product product : listOfProducts)
		{
			if(product != null && product.getProductId() != null &&
					product.getProductId().equals(productId))
			{
				productById=product;
				break;
			}
		}
		if(productById==null)
		{
			throw new IllegalArgumentException("There is no product associated with id: "+productId);
		}
		return productById;
	}
}
