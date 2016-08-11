package godziszewski.patryk.ElectronicsStore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import godziszewski.patryk.ElectronicsStore.domain.Customer;
import godziszewski.patryk.ElectronicsStore.domain.repository.CustomerRepository;
import godziszewski.patryk.ElectronicsStore.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	
	CustomerRepository customerRepository;
	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.getCustomerByEmail(email);
	}
	@Override
	public void update(String customerId,Customer customer) {
		customerRepository.update(customerId, customer);
	}


	

}
