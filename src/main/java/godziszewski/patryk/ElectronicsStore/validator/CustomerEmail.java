package godziszewski.patryk.ElectronicsStore.validator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;


import javax.validation.Constraint;
import javax.validation.Payload;


@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Constraint(validatedBy = CustomerEmailValidator.class)
@Documented
public @interface CustomerEmail {
	String message() default "{godziszewski.patryk.ElectronicsStore.validator.email.message}";
	Class<?>[] groups() default {};
	public abstract Class<? extends Payload>[] payload() default{};
}
