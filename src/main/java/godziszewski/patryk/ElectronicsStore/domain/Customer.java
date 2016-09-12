package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import godziszewski.patryk.ElectronicsStore.validator.CustomerEmail;
import godziszewski.patryk.ElectronicsStore.validator.ValidateOnCreationOnly;

@Entity
@Table(name="Customers")
public class Customer implements Serializable{
	private static final long serialVersionUID = 7565769627033046336L;
	@Id
	@Column(name = "CustomerID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	//can not duplicate emails in database
	@Size(min = 3, max = 60, message = "{Size.Customer.email.validation}")
	@CustomerEmail(groups = ValidateOnCreationOnly.class)
	@Email
	@Column(name = "Email", nullable = false, length = 60)
	private String email;
	@NotNull
	@Size(min = 5, max = 60, message = "{Size.Customer.password.validation}")
	@Column(name = "Password", nullable = false, length = 60)
	private String password;
	@Size(min = 3, max = 30, message = "{Size.Customer.name.validation}")
	@Column(name = "Name", length = 30)
	private String name;
	@Size(min = 3, max = 30, message = "{Size.Customer.surname.validation}")
	@Column(name = "Surname", length = 30)
	private String surname;
	@Size(min = 3, max = 50, message = "{Size.Customer.streetName.validation}")
	@Column(name = "StreetName", length = 50)
	private String streetName;
	@Min(value = 1, message = "{Min.Customer.doorNo.validation}")
	@Column(name = "DoorNo")
	private String doorNo;
	@Size(min = 3, max = 20, message = "{Size.Customer.areaName.validation}")
	@Column(name = "AreaName", length = 20)
	private String areaName;
	@Size(min = 3, max = 50, message = "{Size.Customer.state.validation}")
	@Column(name = "State", length = 50)
	private String state;
	@Size(min = 3, max = 25, message = "{Size.Customer.country.validation}")
	@Column(name = "Country", length = 25)
	private String country;
	@Size(min = 2, max = 10, message = "{Size.Customer.zipCode.validation}")
	@Column(name = "ZipCode", length = 10)
	private String zipCode;
	@Pattern(regexp="(^$|[0-9]{9})", message = "{Pattern.Customer.phoneNumber.validation}") 
	@Column(name = "PhoneNumber", nullable = false, length = 15)
	private String phoneNumber;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="OrderID")
	private Set<Order> orders;
	
	public Customer()
	{
		super();
	}
	public Customer(Integer customerId,String email, String name) {
		super();
		this.customerId = customerId;
		this.email = email;
		this.name = name;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getDoorNo() {
		return doorNo;
	}
	public void setDoorNo(String doorNo) {
		this.doorNo = doorNo;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public Set<Order> getOrders() {
		return orders;
	}
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		Customer other = (Customer) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}
}
