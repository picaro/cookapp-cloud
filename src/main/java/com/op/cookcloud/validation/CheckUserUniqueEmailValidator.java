package com.op.cookcloud.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import com.op.cookcloud.dao.impl.PersonDao;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CheckUserUniqueEmailValidator implements ConstraintValidator<CheckUserUniqueEmail, Object> {

	@Autowired
	private PersonDao personDao;
	private boolean skipCurrent;
	private String emailFieldName;

	@Override
	public boolean isValid(final Object value, final ConstraintValidatorContext constraintContext) {
        log.debug("is email uniq?");
        return true;
//		try {
//			final String email = (String) value;
//
//			if (email == null) {
//                log.debug("email is null");
//                return true;
//			}
//
//			return !personDao.doesUserExistWithEmail(email);
//		} catch (Exception exception) {
//			throw new ValidationException(exception);
//		}

	}

	@Override
	public void initialize(final CheckUserUniqueEmail constraintAnnotation) {
		emailFieldName = constraintAnnotation.email();
		skipCurrent = constraintAnnotation.skipCurrent();
	}

}