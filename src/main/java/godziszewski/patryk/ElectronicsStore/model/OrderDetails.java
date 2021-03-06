package godziszewski.patryk.ElectronicsStore.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="OrderDetails")
public class OrderDetails implements Serializable{
	private static final long serialVersionUID = 1080967130345703888L;
	@Id
	@Column(name = "OrderDetailsID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderDetailsID;
	@ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinColumn(name = "OrderID") 
	private Order order;
	@ManyToOne(cascade = CascadeType.MERGE, fetch=FetchType.LAZY)
    @JoinColumn(name = "ProductID") 
	private Product product;
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	@Column(name = "UnitPrice", nullable = false)
	private BigDecimal unitPrice;
	public OrderDetails()
	{
		super();
	}
	public Integer getOrderDetailsID() {
		return orderDetailsID;
	}
	public void setOrderDetailsID(Integer orderDetailsID) {
		this.orderDetailsID = orderDetailsID;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
