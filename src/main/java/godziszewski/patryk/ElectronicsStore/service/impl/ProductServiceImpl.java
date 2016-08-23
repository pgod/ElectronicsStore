package godziszewski.patryk.ElectronicsStore.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}
	public Product getProductById(Integer productId) {
		return productRepository.getProductById(productId);
	}
	public List<Product> getProductsByCategory(String category) {
		return productRepository.getProductsByCategory(category);
	}
	public 	List <Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		return productRepository.getProductsByFilter(filterParams);
	}
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}
	@Override
	public void updateProduct(Product product) {
		Product p = productRepository.getProductById(product.getProductId());
        if(p!=null){
            p.setName(product.getName());
            p.setUnitPrice(product.getUnitPrice());
            p.setDescription(product.getDescription());
            p.setManufacturer(product.getManufacturer());
            p.setCategory(product.getCategory());
            p.setUnitsInStock(product.getUnitsInStock());
            p.setDiscontinued(product.isDiscontinued());
        }
	}
}
