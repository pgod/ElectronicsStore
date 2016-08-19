package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.domain.Customer;
import godziszewski.patryk.ElectronicsStore.domain.repository.CustomerRepository;
import godziszewski.patryk.ElectronicsStore.exception.CustomerNotFoundException;

@Repository
public class InMemoryCustomerRepository implements CustomerRepository, UserDetailsService {


	   
	  
	Map<String,Customer> listOfCustomers = new HashMap<String,Customer>();
	
	
	public InMemoryCustomerRepository()
	{
		
		Customer c1= new Customer(1,"admin@gmail.com", "Jan");
		c1.setSurname("Kowalski");
		c1.setPassword("password");
		Customer c2= new Customer(1,"user@gmail.com", "Micha³");
		c2.setSurname("Bia³ek");
		c2.setPassword("password");
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
	@Override
	public void save(Customer customer) {
		
		listOfCustomers.put(customer.getEmail(), customer);

		
		
		
	}
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

	Customer customer=getCustomerByEmail(email);
	if(customer==null)
	{
	throw new UsernameNotFoundException("Customer not found '" + email + "'.");
	}
	List<GrantedAuthority> authorities =
			new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("ROLE_SPITTER"));
			return new User( 
			customer.getEmail(),
			customer.getPassword(),
			authorities);
	}
}
