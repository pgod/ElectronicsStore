package godziszewski.patryk.ElectronicsStore.dao.impl;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.dao.OrderDetailsRepository;
import godziszewski.patryk.ElectronicsStore.model.OrderDetails;

@Repository
public class OrderDetailsDAO extends AbstractDAO<Integer, OrderDetails> implements OrderDetailsRepository  {

	@Override
	public void save(OrderDetails orderDetails) {
		persist(orderDetails);
	}
}
