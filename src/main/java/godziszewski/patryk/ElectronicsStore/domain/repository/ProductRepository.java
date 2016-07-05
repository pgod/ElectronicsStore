package godziszewski.patryk.ElectronicsStore.domain.repository;

import java.util.List;

import godziszewski.patryk.ElectronicsStore.domain.Product;

public interface ProductRepository {
	List <Product> getAllProducts();
	Product getProductById(String productId);
}