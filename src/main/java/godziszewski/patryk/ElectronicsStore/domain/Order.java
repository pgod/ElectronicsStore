package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;

import org.joda.time.DateTime;

public class Order implements Serializable{
	private static final long serialVersionUID = -2901273482515323991L;
	private Long orderId;
	private Cart cart;
	private Customer customer;
	private OrderDetails orderDetails;
	private final DateTime orderDate;
	private DateTime shippingDate;
	public Order()
	{
		this.customer = new Customer();
		this.orderDetails = new OrderDetails();
		this.orderDate=new DateTime();
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public OrderDetails getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(OrderDetails orderDetails) {
		this.orderDetails = orderDetails;
	}
	public DateTime getOrderDate() {
		return orderDate;
	}
	public DateTime getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(DateTime shippingDate) {
		this.shippingDate = shippingDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}
	
}
