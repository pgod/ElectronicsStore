package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShippingDetail implements Serializable{
	private static final long serialVersionUID = 1080967130345703888L;
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date shippingDate;
	private Address shippingAddress;
	public ShippingDetail()
	{
		this.shippingAddress = new Address();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getShippingDate() {
		return shippingDate;
	}
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

}
