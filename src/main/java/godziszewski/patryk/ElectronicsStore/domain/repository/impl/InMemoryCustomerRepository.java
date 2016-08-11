package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.domain.Customer;
import godziszewski.patryk.ElectronicsStore.domain.repository.CustomerRepository;
import godziszewski.patryk.ElectronicsStore.exception.CustomerNotFoundException;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository {
	Map<String,Customer> listOfCustomers = new HashMap<String,Customer>();
	public InMemoryCustomerRepository()
	{
		Customer c1= new Customer("admin@gmail.com", "Jan");
		c1.setSurname("Kowalski");
		Customer c2= new Customer("user@gmail.com", "Micha³");
		c2.setSurname("Bia³ek");
		listOfCustomers.put(c1.getEmail(),c1);
		listOfCustomers.put(c2.getEmail(),c2);
	}
	@Override
	public Customer getCustomerByEmail(String email) {
		Customer customerByEmail = null;
		for(Map.Entry<String, Customer> entry : listOfCustomers.entrySet())
		{
			Customer c = entry.getValue();
			if(c.getEmail().equals(email))
			{
				customerByEmail = c;
				break;
			}
		}
		if(customerByEmail==null)
		{
			throw new CustomerNotFoundException(email);
		}
		return customerByEmail;
	}

	@Override
	public void update(String email,Customer customer) {
		if(!listOfCustomers.containsKey(email))
		{
			throw new CustomerNotFoundException(email);
		}
		customer.setEmail(getCustomerByEmail(email).getEmail());
		listOfCustomers.put(email, customer);
		
	}

}
