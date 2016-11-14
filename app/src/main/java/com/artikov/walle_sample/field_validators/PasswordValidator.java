package com.artikov.walle_sample.field_validators;

import android.support.annotation.StringRes;

import com.artikov.walle.Form;
import com.artikov.walle.field_validators.SimpleFieldValidator;

/**
 * Date: 12/11/2016
 * Time: 21:14
 *
 * @author Artur Artikov
 */
public class PasswordValidator extends SimpleFieldValidator<String> {
	public PasswordValidator(@StringRes int errorMessageResId) {
		super(errorMessageResId);
	}

	public PasswordValidator(String errorMessageString) {
		super(errorMessageString);
	}

	@Override
	protected boolean isValid(Form form, String value) {
		return value.length() >= 6;
	}
}
