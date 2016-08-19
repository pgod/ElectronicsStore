package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;
import java.math.BigDecimal;


public class OrderDetails implements Serializable{
	private static final long serialVersionUID = 1080967130345703888L;
	private Integer orderDetailsId;
	private Integer quantity;
	private BigDecimal unitPrice;
	public OrderDetails()
	{
		super();
		
	}
	public Integer getOrderDetailsId() {
		return orderDetailsId;
	}
	public void setOrderDetailsId(Integer orderDetailsId) {
		this.orderDetailsId = orderDetailsId;
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
