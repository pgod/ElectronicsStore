package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.domain.Order;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderRepository;

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
