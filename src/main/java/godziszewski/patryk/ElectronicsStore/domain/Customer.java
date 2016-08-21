package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
@Entity
@Table(name="Customers")
public class Customer implements Serializable{
	private static final long serialVersionUID = 7565769627033046336L;
	@Id
	@Column(name = "CustomerID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer customerId;
	@Email
	@Column(name = "Email", nullable = false)
	private String email;
	@NotNull
	@Size(min=5, max=25, message="{Size.Customer.password.validation}")
	@Column(name = "Password", nullable = false)
	private String password;
	@Size(min = 3, max = 50, message = "{Size.Customer.name.validation}")
	@Column(name = "Name", nullable = false)
	private String name;
	@Size(min = 3, max = 50, message = "{Size.Customer.surname.validation}")
	@Column(name = "Surname", nullable = false)
	private String surname;
	@Column(name = "StreetName")
	private String streetName;
	@Column(name = "DoorNo")
	private String doorNo;
	@Column(name = "AreaName")
	private String areaName;
	@Column(name = "State")
	private String state;
	@Column(name = "Country")
	private String country;
	@Column(name = "ZipCode")
	private String zipCode;
	//@NotEmpty
	@Pattern(regexp="(^$|[0-9]{9})") 
	@Column(name = "PhoneNumber", nullable = false)
	private String phoneNumber;
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
