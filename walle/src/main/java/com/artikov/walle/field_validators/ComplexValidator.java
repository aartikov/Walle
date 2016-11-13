package com.artikov.walle.field_validators;

import com.artikov.walle.Field;
import com.artikov.walle.Form;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FieldValidator;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 12/11/2016
 * Time: 20:49
 *
 * @author Artur Artikov
 */
public class ComplexValidator<T> extends FieldValidator<T> {
    private List<FieldValidator<T>> mValidators;

    @SafeVarargs
    public ComplexValidator(FieldValidator<T>... validators) {
        mValidators = Arrays.asList(validators);
    }

    @Override
    public FieldValidationResult validate(Form form, Field<T> field) {
        for (FieldValidator<T> validator : mValidators) {
            FieldValidationResult result = validator.validate(form, field);
            if (!result.isValid()) {
                return result;
            }
        }
        return FieldValidationResult.createValid(field);
    }
}
