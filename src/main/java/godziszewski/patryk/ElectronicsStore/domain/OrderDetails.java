package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="OrderDetails")
@AssociationOverrides({
	@AssociationOverride(name = "pk.order",
		joinColumns = @JoinColumn(name = "OrderID")),
	@AssociationOverride(name = "pk.product",
		joinColumns = @JoinColumn(name = "ProductID")) })
public class OrderDetails implements Serializable{
	private static final long serialVersionUID = 1080967130345703888L;
	
	
	private OrderDetailsID pk = new OrderDetailsID();
	
	@Column(name = "Quantity", nullable = false)
	private Integer quantity;
	@Column(name = "UnitPrice", nullable = false)
	private BigDecimal unitPrice;
	public OrderDetails()
	{
		super();
		
	}
	
	@EmbeddedId
	public OrderDetailsID getPk() {
		return pk;
	}
	public void setPk(OrderDetailsID pk) {
		this.pk = pk;
	}

	@Transient
	public Order getOrder() {
		return getPk().getOrder();
	}

	public void setOrder(Order order) {
		getPk().setOrder(order);
	}

	@Transient
	public Product getProduct() {
		return getPk().getProduct();
	}

	public void setProduct(Product product) {
		getPk().setProduct(product);
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
