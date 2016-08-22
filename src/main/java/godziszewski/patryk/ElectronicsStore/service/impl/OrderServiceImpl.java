package godziszewski.patryk.ElectronicsStore.service.impl;

import java.util.Map;

import javax.transaction.TransactionManager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import godziszewski.patryk.ElectronicsStore.domain.Cart;
import godziszewski.patryk.ElectronicsStore.domain.CartItem;
import godziszewski.patryk.ElectronicsStore.domain.Order;
import godziszewski.patryk.ElectronicsStore.domain.OrderDetails;
import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderDetailsRepository;
import godziszewski.patryk.ElectronicsStore.domain.repository.OrderRepository;
import godziszewski.patryk.ElectronicsStore.domain.repository.ProductRepository;
import godziszewski.patryk.ElectronicsStore.service.CartService;
import godziszewski.patryk.ElectronicsStore.service.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductRepository productRepository;
	@Autowired 
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderDetailsRepository orderDetailsRepository;


	public void processOrder(Integer productId, int count) {
		Product productById=productRepository.
				getProductById(productId);
		if(productById.getUnitsInStock() < count)
		{
			throw new IllegalArgumentException("Unfortunately, we do not  have enough units in stock. "
					+ "Current amount of items in stock: "+productById.getUnitsInStock());
		}
		productById.setUnitsInStock(productById.getUnitsInStock() - count);
	}

	public void saveOrder(Order order) {
		Cart cart=order.getCart();
		order.setTotalPrice(cart.getGrandTotal());
		
		
		for (Map.Entry<Integer, CartItem> entry : cart.getCartItems().entrySet())
		{			
			OrderDetails od = new OrderDetails();
			od.setOrder(order);
			Product product = entry.getValue().getProduct();
			od.setProduct(product);
		    od.setQuantity(entry.getValue().getQuantity());
			od.setUnitPrice(product.getUnitPrice());
			orderDetailsRepository.save(od);
		}
		
		cartService.delete(order.getCart().getCartId());
	}

	@Override
	public Order getOrderById(Integer id) {
		return orderRepository.getOrderById(id);
	}
}
