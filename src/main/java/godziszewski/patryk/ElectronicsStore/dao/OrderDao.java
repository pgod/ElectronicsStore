package godziszewski.patryk.ElectronicsStore.dao;

import godziszewski.patryk.ElectronicsStore.model.Order;

public interface OrderDao {
	void saveOrder(Order od);
	Order getOrderById(Integer id);
}
