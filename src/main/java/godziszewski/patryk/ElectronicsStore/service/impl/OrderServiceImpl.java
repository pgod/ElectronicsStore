package godziszewski.patryk.ElectronicsStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import godziszewski.patryk.ElectronicsStore.domain.Order;
import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderRepository;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;
import godziszewski.patryk.ElectronicsStore.service.CartService;
import godziszewski.patryk.ElectronicsStore.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;

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

	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}
}
