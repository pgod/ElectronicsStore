package godziszewski.patryk.ElectronicsStore.dao.impl;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.dao.OrderDetailsDao;
import godziszewski.patryk.ElectronicsStore.model.OrderDetails;

@Repository
public class OrderDetailsDAO extends AbstractDAO<Integer, OrderDetails> implements OrderDetailsDao  {

	@Override
	public void save(OrderDetails orderDetails) {
		persist(orderDetails);
	}
}
