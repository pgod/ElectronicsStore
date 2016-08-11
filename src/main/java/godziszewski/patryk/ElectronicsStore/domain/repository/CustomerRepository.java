package godziszewski.patryk.ElectronicsStore.domain.repository;

import godziszewski.patryk.ElectronicsStore.domain.Customer;

public interface CustomerRepository {
	Customer getCustomerByEmail(String id);
	void update(String customerEmail, Customer customer);
}
