package godziszewski.patryk.ElectronicsStore.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import godziszewski.patryk.ElectronicsStore.dao.OrderDao;
import godziszewski.patryk.ElectronicsStore.model.Cart;
import godziszewski.patryk.ElectronicsStore.model.CartItem;
import godziszewski.patryk.ElectronicsStore.model.Order;
import godziszewski.patryk.ElectronicsStore.model.OrderDetails;
import godziszewski.patryk.ElectronicsStore.model.Product;
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
	private OrderDao orderDao;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderDetailsService orderDetailsService;


	public void checkQuantity(Order order) {
		Cart cart=order.getCart();
		for (Map.Entry<Integer, CartItem> entry : cart.getCartItems().entrySet())
		{
			Product product = entry.getValue().getProduct();
			int quantity = entry.getValue().getQuantity();
			Product productById=productService.
					getProductById(product.getProductId());
			if(productById.getUnitsInStock() < quantity)
			{
				throw new IllegalArgumentException("Unfortunately, we do not  have enough units in stock. "
						+ "Current amount of items in stock: "+productById.getUnitsInStock());
			}
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
		return orderDao.getOrderById(id);
	}
}
