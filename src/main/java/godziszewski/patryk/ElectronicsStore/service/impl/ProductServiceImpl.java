package godziszewski.patryk.ElectronicsStore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;
	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

}
