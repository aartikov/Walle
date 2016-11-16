package com.artikov.wallesample.validators;

import android.support.annotation.StringRes;
import android.util.Patterns;

import com.artikov.walle.validators.field.PatternValidator;

/**
 * Date: 12/11/2016
 * Time: 21:16
 *
 * @author Artur Artikov
 */
public class EmailValidator extends PatternValidator {
	public EmailValidator(@StringRes int errorMessageResId) {
		super(Patterns.EMAIL_ADDRESS, errorMessageResId);
	}

	public EmailValidator(String errorMessageString) {
		super(Patterns.EMAIL_ADDRESS, errorMessageString);
	}
}
