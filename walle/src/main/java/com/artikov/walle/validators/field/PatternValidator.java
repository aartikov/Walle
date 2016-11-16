package com.artikov.walle.validators.field;

import java.util.regex.Pattern;

import android.support.annotation.StringRes;

import com.artikov.walle.Form;

/**
 * Date: 12/11/2016
 * Time: 20:57
 *
 * @author Artur Artikov
 */
public class PatternValidator extends SimpleFieldValidator<String> {
	private Pattern mPattern;

	public PatternValidator(Pattern pattern, @StringRes int errorMessageResId) {
		super(errorMessageResId);
		mPattern = pattern;
	}

	public PatternValidator(Pattern pattern, String errorMessageString) {
		super(errorMessageString);
		mPattern = pattern;
	}

	public PatternValidator(String regex, @StringRes int errorMessageResId) {
		super(errorMessageResId);
		mPattern = Pattern.compile(regex);
	}

	public PatternValidator(String regex, String errorMessageString) {
		super(errorMessageString);
		mPattern = Pattern.compile(regex);
	}

	@Override
	protected boolean isValid(Form form, String value) {
		return mPattern.matcher(value).matches();
	}
}
