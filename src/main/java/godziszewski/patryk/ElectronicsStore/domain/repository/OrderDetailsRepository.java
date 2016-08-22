package godziszewski.patryk.ElectronicsStore.domain.repository;

import godziszewski.patryk.ElectronicsStore.domain.OrderDetails;

public interface OrderDetailsRepository {
	public void save(OrderDetails orderDetails);
}
