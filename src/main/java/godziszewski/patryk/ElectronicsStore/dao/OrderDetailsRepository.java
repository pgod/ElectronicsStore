package godziszewski.patryk.ElectronicsStore.dao;

import godziszewski.patryk.ElectronicsStore.model.OrderDetails;

public interface OrderDetailsRepository {
	public void save(OrderDetails orderDetails);
}
