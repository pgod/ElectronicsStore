package godziszewski.patryk.ElectronicsStore.dao;

import godziszewski.patryk.ElectronicsStore.model.Order;

public interface OrderRepository {
	void saveOrder(Order od);
	Order getOrderById(Integer id);
}
