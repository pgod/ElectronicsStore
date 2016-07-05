package godziszewski.patryk.ElectronicsStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;
import godziszewski.patryk.ElectronicsStore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductRepository productRepository;

	public void processOrder(String productId, int count) {
		Product productById=productRepository.
				getProductById(productId);
		if(productById.getUnitsInStock() < count)
		{
			throw new IllegalArgumentException("Unfortunately, we do not  have enough units in stock. "
					+ "Current amount of items in stock: "+productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock() - count);
	}
}
