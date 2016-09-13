package godziszewski.patryk.ElectronicsStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import godziszewski.patryk.ElectronicsStore.dao.OrderDetailsDao;
import godziszewski.patryk.ElectronicsStore.model.OrderDetails;
import godziszewski.patryk.ElectronicsStore.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsDao orderDetailsDao;
	@Override
	public void save(OrderDetails orderDetails) {
		orderDetailsDao.save(orderDetails);
	}

}
