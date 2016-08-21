package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table(name="Orders")
public class Order implements Serializable{
	private static final long serialVersionUID = -2901273482515323991L;
	@Id
	@Column(name = "OrderID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	@Transient
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "CustomerID")
	private Customer customer;

	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.order")
	private Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();
	
	@DateTimeFormat(pattern="dd/MM/yyyy") 
    @Column(name = "OrderDate", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private final LocalDate orderDate;
	@DateTimeFormat(pattern="dd/MM/yyyy") 
    @Column(name = "ShippingDate", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate shippingDate;
	@Column(name = "TotalPrice")
	private BigDecimal totalPrice;
	public Order()
	{
		this.orderDate=new LocalDate();
		this.shippingDate=new LocalDate();
		this.totalPrice = new BigDecimal(0);
	}
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer Id) {
		this.orderId = Id;
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


	public LocalDate getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}
	
	
	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public LocalDate getOrderDate() {
		return orderDate;
	}
	
	public BigDecimal getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
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
