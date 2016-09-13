package godziszewski.patryk.ElectronicsStore.dao;

import java.util.List;
import java.util.Map;

import godziszewski.patryk.ElectronicsStore.model.Product;

public interface ProductRepository {
	List <Product> getAllProducts();
	Product getProductById(Integer productId);
	List <Product> getProductsByCategory(String category);
	List <Product> getProductsByFilter(Map<String, List<String>> filterParams); 
	void addProduct(Product product);
}