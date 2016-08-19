package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.domain.Order;

public interface OrderService {
	void processOrder(Integer productId, int count);
	Long saveOrder(Order order);
}
