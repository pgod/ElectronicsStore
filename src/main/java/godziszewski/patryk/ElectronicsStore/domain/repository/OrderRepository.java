package godziszewski.patryk.ElectronicsStore.domain.repository;

import godziszewski.patryk.ElectronicsStore.domain.Order;

public interface OrderRepository {
	void saveOrder(Order od);
	Order getOrderById(Integer id);
}
