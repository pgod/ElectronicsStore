package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.model.Order;

public interface OrderService {
	void checkQuantity(Order order);
	void saveOrder(Order order);
	Order getOrderById(Integer id);
}
