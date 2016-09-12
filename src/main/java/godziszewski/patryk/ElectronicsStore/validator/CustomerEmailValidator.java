package godziszewski.patryk.ElectronicsStore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import godziszewski.patryk.ElectronicsStore.service.CustomerService;

public class CustomerEmailValidator implements ConstraintValidator<CustomerEmail, String> {
	
	@Autowired
	private CustomerService customerService;

	@Override
	public void initialize(CustomerEmail email) {
		
	}

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return customerService.getCustomerByEmail(email)==null;
	}
}
