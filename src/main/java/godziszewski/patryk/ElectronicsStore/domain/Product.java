package godziszewski.patryk.ElectronicsStore.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;



@XmlRootElement
@JsonInclude(Include.NON_EMPTY)
@Entity
@Table(name="Products")
public class Product implements Serializable{
	private static final long serialVersionUID = -7013955470939575675L;
	@Id
	@Column(name = "ProductID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	@Size(min = 4, max = 40, message = "{Size.Product.name.validation}")
	@Column(name = "Name", nullable = false, length = 40)
	private String name;
	@Min(value = 0, message = "{Min.Product.UnitPrice.validation}")
	@Digits(integer = 10, fraction = 2, message = "{Digits.Product.unitPrice.validation}")
	@NotNull(message = "{NotNull.Product.unitPrice.validation}")
	@Column(name = "UnitPrice", nullable = false)
	private BigDecimal unitPrice;
	@Column(name = "Description", nullable = false, length = 300)
	@NotBlank(message = "{NotBlank.Product.description.validation}")
	private String description;
	@Column(name = "Manufacturer", nullable = false, length = 30)
	@Length(min = 1, max = 30, message = "{Length.Product.manufacturer.validation}")
	private String manufacturer;
	@NotNull(message = "{NotNull.Product.category.validation}")
	@Length(min = 1, max = 20, message = "{Length.Product.category.validation}")
	@Column(name = "Category", nullable = false, length = 20)
	private String category;
	@Min(value = 0, message = "{Min.Product.UnitsInStock.validation}")
	@Column(name = "UnitsInStock", nullable = false)
	private long unitsInStock;
	@Column(name = "Discontinued", nullable = false)
	private boolean discontinued;
	//does problems with cart overview
	
	//has to be fetched eagerly for REST services
	@OneToMany(mappedBy = "product", fetch=FetchType.EAGER)
	private Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();
	//product images are not stored in database
	@Transient
	private MultipartFile productImage;
	
	public Product()
	{
		super();
	}
	public Product(Integer productId, String name, BigDecimal unitPrice)
	{
		this.productId=productId;
		this.name=name;
		this.unitPrice=unitPrice;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public long getUnitsInStock() {
		return unitsInStock;
	}
	public void setUnitsInStock(long unitsInStock) {
		this.unitsInStock = unitsInStock;
	}
	public boolean isDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(boolean discontinued) {
		this.discontinued = discontinued;
	}
	@XmlTransient
	public MultipartFile getProductImage() {
		return productImage;
	}
	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}
	@XmlTransient
	public Set<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(Set<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
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
		Product other = (Product) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Product [productId= "+productId+", name="+name+"]";
	}
	
}
