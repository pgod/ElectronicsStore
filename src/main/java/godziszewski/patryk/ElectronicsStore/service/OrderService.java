package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.domain.Order;

public interface OrderService {
	void processOrder(Integer productId, int count);
	void saveOrder(Order order);
	Order getOrderById(Integer id);
}
