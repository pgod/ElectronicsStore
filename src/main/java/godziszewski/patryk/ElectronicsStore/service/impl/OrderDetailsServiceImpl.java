package godziszewski.patryk.ElectronicsStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import godziszewski.patryk.ElectronicsStore.domain.OrderDetails;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderDetailsRepository;
import godziszewski.patryk.ElectronicsStore.service.OrderDetailsService;

public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepository orderDetailsRepository;
	@Override
	public void save(OrderDetails orderDetails) {
		orderDetailsRepository.save(orderDetails);
	}

}
