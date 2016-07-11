package godziszewski.patryk.ElectronicsStore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class CategoryValidator implements ConstraintValidator<Category,String> {


	
	public void initialize(Category arg0) {
		// TODO Auto-generated method stub
		
	}
	public CategoryValidator()
	{
		Category.allowedCategories.add("laptop");
		Category.allowedCategories.add("tablet");
		Category.allowedCategories.add("smart phone");
	}
	public boolean isValid(String value, ConstraintValidatorContext context) {
	
		if(Category.allowedCategories.contains(value))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
}
