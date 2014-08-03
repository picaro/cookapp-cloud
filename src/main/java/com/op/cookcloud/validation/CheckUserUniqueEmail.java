package com.op.cookcloud.validation;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CheckUserUniqueEmailValidator.class)
@Documented
public @interface CheckUserUniqueEmail {

	String message() default "{email.already.exists}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * 
	 * @return users email
	 */
	String email();

	/**
	 * A key for spring error mapping
	 * 
	 * @return
	 */
	String errorKey() default "bean";

	boolean skipCurrent() default true;

	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		CheckUserUniqueEmail[] value();
	}

}