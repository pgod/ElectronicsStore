package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.domain.Customer;

public interface CustomerService {
	Customer getCustomerByEmail(String email);
	void update(String customerId, Customer customer);
	void create(Customer customer);
}
