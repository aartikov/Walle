package com.artikov.walle.validators.field;

import android.support.annotation.StringRes;

import com.artikov.walle.Field;
import com.artikov.walle.Form;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FieldValidator;
import com.artikov.walle.StringWrapper;

/**
 * Date: 12/11/2016
 * Time: 17:49
 *
 * @author Artur Artikov
 */
public abstract class SimpleFieldValidator<T> extends FieldValidator<T> {
	private FieldValidationResult invalidResult;

	public SimpleFieldValidator(@StringRes int errorMessageResId) {
		invalidResult = new FieldValidationResult(false, new StringWrapper(errorMessageResId));
	}

	public SimpleFieldValidator(String errorMessageString) {
		invalidResult = new FieldValidationResult(false, new StringWrapper(errorMessageString));
	}

	@Override
	public FieldValidationResult validate(Form form, Field<T> field) {
		T value = form.getValue(field);
		if (value == null) {
			throw new IllegalArgumentException("SimpleFieldValidator: value of field " + field.getName() + " is null");
		}
		return isValid(form, value) ? FieldValidationResult.VALID : invalidResult;
	}

	abstract protected boolean isValid(Form form, T value);
}
