package godziszewski.patryk.ElectronicsStore.service;

import godziszewski.patryk.ElectronicsStore.model.Customer;

public interface CustomerService {
	Customer getCustomerByEmail(String email);
	void update(Customer customer);
	void create(Customer customer);
	Customer getCustomerById(Integer id);
	void updateAddressDetails(Customer customer);
}
