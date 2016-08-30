package godziszewski.patryk.ElectronicsStore.exception;

public class CustomerNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -2481851419054471912L;
	private String email;
	public CustomerNotFoundException(String email)
	{
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	@Override
	public String toString() {
		return "Customer with email:"+email+" not found";
		
	}
}
