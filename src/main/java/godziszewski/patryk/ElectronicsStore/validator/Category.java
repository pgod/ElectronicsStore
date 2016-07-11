package godziszewski.patryk.ElectronicsStore.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Constraint;
import javax.validation.Payload;


@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CategoryValidator.class)
@Documented
public @interface Category {
	String message() default "{godziszewski.patryk.ElectronicsStore.validator.category.message}";
	Class<?>[] groups() default {};
	public abstract Class<? extends Payload>[] payload() default{};
	List<String> allowedCategories  = new ArrayList <String>();
}
