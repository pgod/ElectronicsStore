package godziszewski.patryk.ElectronicsStore.domain.repository;

import godziszewski.patryk.ElectronicsStore.domain.Order;

public interface OrderRepository {
	Long saveOrder(Order order);
}
