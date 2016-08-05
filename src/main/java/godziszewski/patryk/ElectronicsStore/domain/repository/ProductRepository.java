package godziszewski.patryk.ElectronicsStore.domain.repository;

import java.util.List;
import java.util.Map;

import godziszewski.patryk.ElectronicsStore.domain.Product;

public interface ProductRepository {
	List <Product> getAllProducts();
	Product getProductById(String productId);
	List <Product> getProductsByCategory(String category);
	List <Product> getProductsByFilter(Map<String, List<String>> filterParams); 
	void addProduct(Product product);
}