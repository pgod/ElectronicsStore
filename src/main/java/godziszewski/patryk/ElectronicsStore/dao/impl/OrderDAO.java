package godziszewski.patryk.ElectronicsStore.dao.impl;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.dao.OrderRepository;
import godziszewski.patryk.ElectronicsStore.model.Order;

@Repository
public class OrderDAO extends AbstractDAO<Integer, Order> implements OrderRepository {

	@Override
	public void saveOrder(Order order) {
		persist(order);
	}
	public Order getOrderById(Integer id)
	{
		return getByKey(id);
	}
}
