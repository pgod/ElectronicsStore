package godziszewski.patryk.ElectronicsStore.dao;

import godziszewski.patryk.ElectronicsStore.model.Customer;

public interface CustomerRepository {
	Customer getCustomerByEmail(String email);
	void save(Customer customer);
	Customer getCustomerById(Integer id);
}
