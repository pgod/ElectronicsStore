package godziszewski.patryk.ElectronicsStore.service;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import godziszewski.patryk.ElectronicsStore.config.RootConfig;
import godziszewski.patryk.ElectronicsStore.model.Customer;
import godziszewski.patryk.ElectronicsStore.model.Order;

import static org.junit.Assert.*;
@ActiveProfiles("InMemoryDatabaseDatabase")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class CustomerServiceIntegrationTest {
	@Autowired
	CustomerService customerService;
	@Autowired
	OrderService orderService;
	
	@Test
	@Rollback(true)
	@Transactional
	public void addCorrectCustomerTest()
	{
		Customer newCustomer = this.createCustomer();
		customerService.create(newCustomer);
		
		Customer readCustomer = customerService.getCustomerByEmail(newCustomer.getEmail());
		
		Assert.notNull(readCustomer);
		assertEquals(newCustomer, readCustomer);
	}
	@Test
	@Rollback(true)
	@Transactional
	public void addIncorrectCustomerTest()
	{
		Exception caughtException = null;
		Customer newCustomer = new Customer();
		newCustomer.setName("");
		newCustomer.setEmail("");
		newCustomer.setPassword("");
		
		try{
			customerService.create(newCustomer);
		}catch (Exception ex)
		{
			caughtException = ex;
		}
		
		Assert.notNull(caughtException);
	}
	@Test
	@Rollback(true)
	@Transactional
	public void getIncorrectCustomerByEmailTest()
	{
		Customer foundCustomer = customerService.getCustomerByEmail("");
		
		assertNull(foundCustomer);
	}
	@Test
	@Rollback(true)
	@Transactional
	public void updateCorrectCustomerTest()
	{
		Customer oldCustomer = this.createCustomer();
		customerService.create(oldCustomer);
		Customer newCustomer = new Customer();
		newCustomer.setEmail(oldCustomer.getEmail());
		newCustomer.setName("Micha³");
		newCustomer.setSurname("Bia³ek");
		newCustomer.setAreaName("Warszawa");
		newCustomer.setState("Wykopowa");
		newCustomer.setDoorNo("19");
		newCustomer.setPassword("haslo");
		newCustomer.setZipCode("11-111");
		newCustomer.setPhoneNumber("100200300");
		
		customerService.update(newCustomer);
		Customer foundCustomer = customerService.getCustomerByEmail(newCustomer.getEmail());
		
		assertEquals(newCustomer, foundCustomer);
	}
	@Test
	@Rollback(true)
	@Transactional
	public void updateIncorrectCustomerTest()
	{
		Customer customer = new Customer();
		Exception caughtException = null;
		try{
			customerService.update(customer);
		} catch(Exception ex)
		{
			caughtException = ex;
		}
		Assert.notNull(caughtException);
	}
	@Test
	@Rollback(true)
	@Transactional
	public void getCorrectCustomerByIdTest()
	{
		Customer newCustomer = this.createCustomer();
		customerService.create(newCustomer);
		
		Customer foundCustomer = customerService.getCustomerById(newCustomer.getCustomerId());

		assertEquals(newCustomer, foundCustomer);
	}
	@Test
	@Rollback(true)
	@Transactional
	public void getIncorrectCustomerByIdTest()
	{	
		Customer foundCustomer = customerService.getCustomerById(-1);

		assertNull(foundCustomer);
	}
	private Customer createCustomer()
	{
		Customer customer = new Customer();
		customer.setName("Jan");
		customer.setSurname("Kowalski");
		customer.setAreaName("Chrzanów");
		customer.setStreetName("Przyk³adowa");
		customer.setCountry("Poland");
		customer.setDoorNo("1");
		customer.setPassword("testPassword");
		customer.setEmail("testEmail@gmail.com");
		customer.setPhoneNumber("123456789");
		customer.setState("ma³opolskie");
		customer.setZipCode("22-100");
		Set<Order> orders = new HashSet<Order>();		
		customer.setOrders(orders);		
		return customer;
	}
}
