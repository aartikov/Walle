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

    private ValidationStrategy mValidationStrategy;
    private Map<Field, FieldValidator> mFieldValidators = new LinkedHashMap<>();
    private FormValidationResult mValidationResult = new FormValidationResult();

    public FormValidator() {
        this(ValidationStrategy.ALL);
    }

    public FormValidator(ValidationStrategy validationStrategy) {
        mValidationStrategy = validationStrategy;
    }

    public <T> void addFieldValidator(Field<T> field, FieldValidator<T> validator) {
        if (mFieldValidators.containsKey(field)) {
            throw new IllegalArgumentException("FormValidator: more than one validator for field " + field.getName());
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
        List<FieldValidationResult> results = new ArrayList<>();
        boolean wasError = false;

        for (Map.Entry<Field, FieldValidator> entry : mFieldValidators.entrySet()) {
            Field field = entry.getKey();
            FieldValidator validator = entry.getValue();

            if (!form.containsField(field)) {
                throw new IllegalArgumentException("FormValidator: form does not contain field " + field.getName());
            }

            FieldValidationResult result;
            if(mValidationStrategy == ValidationStrategy.ALL) {
                result = validator.validate(form, field);
            } else if(mValidationStrategy == ValidationStrategy.UNTIL_FIRST_ERROR) {
                result = wasError ? FieldValidationResult.createValid(field) : validator.validate(form, field);
            } else {
                throw new IllegalArgumentException("FormValidator: unknown validation strategy " + mValidationStrategy);
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