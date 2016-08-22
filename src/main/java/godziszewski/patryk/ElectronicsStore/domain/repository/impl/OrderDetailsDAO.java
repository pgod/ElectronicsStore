package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.domain.OrderDetails;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderDetailsRepository;

@Repository
public class OrderDetailsDAO extends AbstractDAO<Integer, OrderDetails> implements OrderDetailsRepository  {

	@Override
	public void save(OrderDetails orderDetails) {
		persist(orderDetails);
		
	}


}
