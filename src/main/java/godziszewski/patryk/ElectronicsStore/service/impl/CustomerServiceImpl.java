package godziszewski.patryk.ElectronicsStore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import godziszewski.patryk.ElectronicsStore.domain.Customer;
import godziszewski.patryk.ElectronicsStore.domain.repository.CustomerRepository;
import godziszewski.patryk.ElectronicsStore.exception.CustomerNotFoundException;
import godziszewski.patryk.ElectronicsStore.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private final CustomerRepository customerRepository;
	@Autowired
	 public CustomerServiceImpl(CustomerRepository customerRepository) {
		 this.customerRepository=customerRepository;
	}
	
	@Override
	public Customer getCustomerByEmail(String email) {
		return customerRepository.getCustomerByEmail(email);
	}
	@Override
	public void update(Customer customer) {
		Customer entity = customerRepository.getCustomerByEmail(customer.getEmail());
        if(entity == null)
        {
        	throw new CustomerNotFoundException(customer.getEmail());
        }
        entity.setEmail(customer.getEmail());
        entity.setPassword(passwordEncoder.encode(customer.getPassword()));
        entity.setName(customer.getName());
        entity.setSurname(customer.getSurname());
        entity.setStreetName(customer.getStreetName());
        entity.setDoorNo(customer.getDoorNo());
        entity.setAreaName(customer.getAreaName());
        entity.setState(customer.getState());
        entity.setCountry(customer.getCountry());
        entity.setZipCode(customer.getZipCode());
        entity.setPhoneNumber(customer.getPhoneNumber());
	}
	@Override
	public void create(Customer customer) {
		 customer.setPassword(passwordEncoder
				 .encode(customer.getPassword()));
		 customerRepository.save(customer);
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customerRepository.getCustomerById(id);
	}

	@Override
	public void updateAddressDetails(Customer customer) {
		Customer entity = customerRepository.getCustomerByEmail(customer.getEmail());
        if(entity == null)
        {
        	throw new CustomerNotFoundException(customer.getEmail());
        }
        entity.setName(customer.getName());
        entity.setSurname(customer.getSurname());
        entity.setStreetName(customer.getStreetName());
        entity.setDoorNo(customer.getDoorNo());
        entity.setAreaName(customer.getAreaName());
        entity.setState(customer.getState());
        entity.setCountry(customer.getCountry());
        entity.setZipCode(customer.getZipCode());
        entity.setPhoneNumber(customer.getPhoneNumber());
	}
}
