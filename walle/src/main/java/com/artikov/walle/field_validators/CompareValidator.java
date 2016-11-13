package com.artikov.walle.field_validators;

import android.support.annotation.StringRes;

import com.artikov.walle.Field;
import com.artikov.walle.Form;

/**
 * Date: 12/11/2016
 * Time: 21:20
 *
 * @author Artur Artikov
 */
public class CompareValidator<T> extends SimpleFieldValidator<T> {
    private Field<T> mOtherField;

    public CompareValidator(Field<T> otherField, @StringRes int errorMessageResId) {
        super(errorMessageResId);
        mOtherField = otherField;
    }

    public CompareValidator(Field<T> otherField, String errorMessageString) {
        super(errorMessageString);
        mOtherField = otherField;
    }

    @Override
    protected boolean isValid(Form form, T value) {
        T otherValue = form.getValue(mOtherField);
        return otherValue != null && otherValue.equals(value);
    }
}
