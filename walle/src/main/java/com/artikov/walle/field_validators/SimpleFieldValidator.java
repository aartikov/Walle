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
    private FieldValidationResult invalidResult;

    public SimpleFieldValidator(@StringRes int errorMessageResId) {
        invalidResult = new FieldValidationResult(false, errorMessageResId);
    }

    public SimpleFieldValidator(String errorMessageString) {
        invalidResult = new FieldValidationResult(false, errorMessageString);
    }

    @Override
    public FieldValidationResult validate(Form form, Field<T> field) {
        T value = form.getValue(field);
        return isValid(form, value) ? FieldValidationResult.VALID : invalidResult;
    }

    abstract protected boolean isValid(Form form, T value);
}
