package godziszewski.patryk.ElectronicsStore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import godziszewski.patryk.ElectronicsStore.domain.Product;

public interface ProductService {
	List <Product> getAllProducts();
	Product getProductById(String productId);
	List <Product> getProductsByCategory(String category);
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	void addProduct(Product product);
}
