package com.artikov.walle.field_validators;

import android.support.annotation.StringRes;

import com.artikov.walle.Field;
import com.artikov.walle.Form;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FieldValidator;

/**
 * Date: 12/11/2016
 * Time: 17:49
 *
 * @author Artur Artikov
 */
public abstract class SimpleFieldValidator<T> extends FieldValidator<T> {
    @StringRes
    private int mErrorMessageResId = -1;
    private String mErrorMessageString = "";

    public SimpleFieldValidator(@StringRes int errorMessageResId) {
        mErrorMessageResId = errorMessageResId;
    }

    public SimpleFieldValidator(String errorMessageString) {
        mErrorMessageString = errorMessageString;
    }

    @Override
    public FieldValidationResult validate(Form form, Field<T> field) {
        T value = form.getValue(field);
        boolean valid = isValid(form, value);
        if (valid) {
            return FieldValidationResult.createValid(field);
        } else {
            if (mErrorMessageResId != -1) {
                return new FieldValidationResult(field, false, mErrorMessageResId);
            } else {
                return new FieldValidationResult(field, false, mErrorMessageString);
            }
        }
    }

    abstract protected boolean isValid(Form form, T value);
}
