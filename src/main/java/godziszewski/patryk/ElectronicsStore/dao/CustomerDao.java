package godziszewski.patryk.ElectronicsStore.dao;

import godziszewski.patryk.ElectronicsStore.model.Customer;

public interface CustomerDao {
	Customer getCustomerByEmail(String email);
	void save(Customer customer);
	Customer getCustomerById(Integer id);
}
