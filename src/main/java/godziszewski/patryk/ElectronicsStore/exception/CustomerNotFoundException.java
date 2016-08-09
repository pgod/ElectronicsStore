package godziszewski.patryk.ElectronicsStore.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2481851419054471912L;
	private String customerId;
	public CustomerNotFoundException(String customerId)
	{
		this.customerId = customerId;
	}
	public String getCustomerId() {
		return customerId;
	}
}
