package godziszewski.patryk.ElectronicsStore.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import godziszewski.patryk.ElectronicsStore.dao.CustomerDao;
import godziszewski.patryk.ElectronicsStore.exception.CustomerNotFoundException;
import godziszewski.patryk.ElectronicsStore.model.Customer;
import godziszewski.patryk.ElectronicsStore.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	private final CustomerDao customerDao;
	@Autowired
	 public CustomerServiceImpl(CustomerDao customerDao) {
		 this.customerDao=customerDao;
	}
	
	@Override
	public Customer getCustomerByEmail(String email) {
		return customerDao.getCustomerByEmail(email);
	}
	@Override
	public void update(Customer customer) {
		Customer entity = customerDao.getCustomerByEmail(customer.getEmail());
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
		 customerDao.save(customer);
	}

	@Override
	public Customer getCustomerById(Integer id) {
		return customerDao.getCustomerById(id);
	}

	@Override
	public void updateAddressDetails(Customer customer) {
		Customer entity = customerDao.getCustomerByEmail(customer.getEmail());
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
