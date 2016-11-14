package com.artikov.walle.field_validators;

import android.support.annotation.StringRes;
import android.text.TextUtils;

import com.artikov.walle.Form;

/**
 * Date: 12/11/2016
 * Time: 17:39
 *
 * @author Artur Artikov
 */
public class NotEmptyStringValidator extends SimpleFieldValidator<String> {

	public NotEmptyStringValidator(@StringRes int errorMessageResId) {
		super(errorMessageResId);
	}

	public NotEmptyStringValidator(String errorMessageString) {
		super(errorMessageString);
	}

	@Override
	protected boolean isValid(Form form, String value) {
		return !TextUtils.isEmpty(value);
	}
}
