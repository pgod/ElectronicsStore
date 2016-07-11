package godziszewski.patryk.ElectronicsStore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import godziszewski.patryk.ElectronicsStore.domain.Product;
import godziszewski.patryk.ElectronicsStore.exception.ProductNotFoundException;
import godziszewski.patryk.ElectronicsStore.service.ProductService;



public class ProductIdValidator implements ConstraintValidator<ProductId,String> {
	@Autowired
	private ProductService productService;
	public void initialize(ProductId constraingAnnotation)
	{
		
	}
	public boolean isValid(String value, ConstraintValidatorContext context) {
		Product product;
		try {
			
			product = productService.getProductById(value);
			
		} catch (ProductNotFoundException e) {
			return true;
		}
		
		if(product!= null) {
			return false;
		}
		
		return true;
	}

}
