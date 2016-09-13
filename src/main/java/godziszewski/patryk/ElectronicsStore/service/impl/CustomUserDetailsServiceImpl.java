package godziszewski.patryk.ElectronicsStore.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import godziszewski.patryk.ElectronicsStore.exception.CustomerNotFoundException;
import godziszewski.patryk.ElectronicsStore.model.Customer;
import godziszewski.patryk.ElectronicsStore.service.CustomerService;

@Service
@PropertySource("classpath:/superusers.properties")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private CustomerService customerService;
	@Autowired
	Environment environment;
	
	@Value("#{'${admins}'.split(',')}")
 	private List<String> admins;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = customerService.getCustomerByEmail(email);
		if(customer==null)
		{
			throw new CustomerNotFoundException(email);
		}
		return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(), 
                true, true, true, true, getGrantedAuthorities(customer));
	}
	 private List<GrantedAuthority> getGrantedAuthorities(Customer customer){
	        List<GrantedAuthority> authorities = new ArrayList<>();
	        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	        for(String user : admins)
	        {
	        	if(customer.getEmail().equals(user))
	        	{
	        		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	        		break;
	        	}
	        }
	        return authorities;
	    }
}
