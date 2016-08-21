package godziszewski.patryk.ElectronicsStore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import godziszewski.patryk.ElectronicsStore.domain.Order;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderRepository;

//@Repository
public class InMemoryOrderRepository implements OrderRepository {
	private Map <Integer, Order> listOfOrders;
	private long nextOrderId;
	public InMemoryOrderRepository()
	{
		listOfOrders = new HashMap<Integer, Order>();
		nextOrderId = 1000;
	}
	public void saveOrder(Order order) {
		//order.setOrderId(getNextOrderId());
		order.setOrderId(getNextOrderId());
		listOfOrders.put(order.getOrderId(), order);
		
	}
	private synchronized Integer getNextOrderId() 
	{
		return (int) nextOrderId++;
	}


	@Override
	public Order getOrderById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}
}
