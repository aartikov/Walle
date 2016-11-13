package com.artikov.walle.form_validators;

import com.artikov.walle.Field;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FieldValidator;
import com.artikov.walle.Form;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.FormValidator;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 13/11/2016
 * Time: 19:09
 *
 * @author Artur Artikov
 */
public class PerFieldFormValidator extends FormValidator {
    public enum ValidationStrategy {
        ALL,
        UNTIL_FIRST_ERROR
    }

    private ValidationStrategy mValidationStrategy;
    private Map<Field, FieldValidator> mFieldValidators = new LinkedHashMap<>();

    public PerFieldFormValidator() {
        this(ValidationStrategy.ALL);
    }

    public PerFieldFormValidator(ValidationStrategy validationStrategy) {
        mValidationStrategy = validationStrategy;
    }

    public <T> void addFieldValidator(Field<T> field, FieldValidator<T> validator) {
        if (mFieldValidators.containsKey(field)) {
            throw new IllegalArgumentException("PerFieldFormValidator: more than one validator for field " + field.getName());
        }
        mFieldValidators.put(field, validator);
    }

    public ValidationStrategy getValidationStrategy() {
        return mValidationStrategy;
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        mValidationStrategy = validationStrategy;
    }

    @SuppressWarnings("unchecked")
    public FormValidationResult validate(Form form) {
        FormValidationResult result = new FormValidationResult();
        boolean wasError = false;

        for (Map.Entry<Field, FieldValidator> entry : mFieldValidators.entrySet()) {
            Field field = entry.getKey();
            FieldValidator validator = entry.getValue();

            if (!form.containsField(field)) {
                throw new IllegalArgumentException("PerFieldFormValidator: form does not contain field " + field.getName());
            }

            FieldValidationResult fieldValidationResult;
            if(mValidationStrategy == ValidationStrategy.ALL) {
                fieldValidationResult = validator.validate(form, field);
            } else if(mValidationStrategy == ValidationStrategy.UNTIL_FIRST_ERROR) {
                fieldValidationResult = wasError ? FieldValidationResult.VALID : validator.validate(form, field);
            } else {
                throw new IllegalArgumentException("PerFieldFormValidator: unknown validation strategy " + mValidationStrategy);
            }

            if (!fieldValidationResult.isValid()) {
                wasError = true;
            }

            result.putFieldValidationResult(field, fieldValidationResult);
        }

        return result;
    }
}
