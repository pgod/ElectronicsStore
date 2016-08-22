package godziszewski.patryk.ElectronicsStore.domain.repository;

import godziszewski.patryk.ElectronicsStore.domain.Customer;

public interface CustomerRepository {
	Customer getCustomerByEmail(String email);
	void save(Customer customer);
	Customer getCustomerById(Integer id);
}
