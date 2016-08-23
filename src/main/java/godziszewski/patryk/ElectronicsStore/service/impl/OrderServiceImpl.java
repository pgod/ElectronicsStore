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
import godziszewski.patryk.ElectronicsStore.service.OrderDetailsService;
import godziszewski.patryk.ElectronicsStore.service.OrderService;
import godziszewski.patryk.ElectronicsStore.service.ProductService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private ProductService productService;
	@Autowired 
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderDetailsService orderDetailsService;


	public void processOrder(Integer productId, int count) {
		Product productById=productService.
				getProductById(productId);
		if(productById.getUnitsInStock() < count)
		{
			throw new IllegalArgumentException("Unfortunately, we do not  have enough units in stock. "
					+ "Current amount of items in stock: "+productById.getUnitsInStock());
		}
		
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
			
			product.setUnitsInStock(product.getUnitsInStock()-od.getQuantity());
			productService.updateProduct(product);
			
			orderDetailsService.save(od);
		}
		
		cartService.delete(order.getCart().getCartId());
		
	}

	@Override
	public Order getOrderById(Integer id) {
		return orderRepository.getOrderById(id);
	}
}
