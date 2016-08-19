package godziszewski.patryk.ElectronicsStore.exception;

public class ProductNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 848217189300976022L;
	private Integer productId;
	public ProductNotFoundException(Integer productId2)
	{
		this.productId=productId2;
	}
	public Integer getProductId() {
		return productId;
	}
}
