package com.artikov.walle;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 12/11/2016
 * Time: 18:40
 *
 * @author Artur Artikov
 */
public class FormValidator {
    public enum ValidationStrategy {
        ALL,
        UNTIL_FIRST_ERROR
    }

    private Map<Field, FieldValidator> mFieldValidators = new LinkedHashMap<>();
    private FormValidationResult mValidationResult = new FormValidationResult();

    public <T> void addFieldValidator(Field<T> field, FieldValidator<T> validator) {
        if (mFieldValidators.containsKey(field)) {
            throw new IllegalArgumentException("FormValidator: more than one validator for field " + field.getName());
        }
        mFieldValidators.put(field, validator);
    }

    public FormValidationResult validateAll(Form form) {
        return validate(form, ValidationStrategy.ALL);
    }

    public FormValidationResult validateUntilFirstError(Form form) {
        return validate(form, ValidationStrategy.UNTIL_FIRST_ERROR);
    }

    @SuppressWarnings("unchecked")
    public FormValidationResult validate(Form form, ValidationStrategy validationStrategy) {
        List<FieldValidationResult> results = new ArrayList<>();
        boolean wasError = false;

        for (Map.Entry<Field, FieldValidator> entry : mFieldValidators.entrySet()) {
            Field field = entry.getKey();
            FieldValidator validator = entry.getValue();

            if (!form.containsField(field)) {
                throw new IllegalArgumentException("FormValidator: form does not contain field " + field.getName());
            }

            FieldValidationResult result;
            if(validationStrategy == ValidationStrategy.ALL) {
                result = validator.validate(form, field);
            } else if(validationStrategy == ValidationStrategy.UNTIL_FIRST_ERROR) {
                result = wasError ? FieldValidationResult.createValid(field) : validator.validate(form, field);
            } else {
                throw new IllegalArgumentException("FormValidator: unknown validation strategy " + validationStrategy);
            }

            if (!result.isValid()) {
                wasError = true;
            }

            results.add(result);
        }

        mValidationResult = new FormValidationResult(results);
        return mValidationResult;
    }

    public FormValidationResult removeAllErrors() {
        mValidationResult.removeAllErrors();
        return mValidationResult;
    }

    public FormValidationResult removeFieldError(Field field) {
        mValidationResult.removeFieldError(field);
        return mValidationResult;
    }
}