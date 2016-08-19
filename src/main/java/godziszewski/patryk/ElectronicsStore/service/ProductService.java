package godziszewski.patryk.ElectronicsStore.service;

import java.util.List;
import java.util.Map;

import godziszewski.patryk.ElectronicsStore.domain.Product;

public interface ProductService {
	List <Product> getAllProducts();
	Product getProductById(Integer productId);
	List <Product> getProductsByCategory(String category);
	List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	void addProduct(Product product);
}
