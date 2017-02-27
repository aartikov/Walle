package com.artikov.walle.validators.form;

import java.util.HashMap;
import java.util.Map;

import com.artikov.walle.Field;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FieldValidator;
import com.artikov.walle.Form;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.FormValidator;

/**
 * Date: 13/11/2016
 * Time: 19:09
 *
 * @author Artur Artikov
 */
public class StandardFormValidator extends FormValidator {

    public enum ValidationStrategy {
        ALL,
        UNTIL_FIRST_ERROR
    }

    private ValidationStrategy mValidationStrategy;
    private Map<Field, FieldValidator> mFieldValidators = new HashMap<>();
    private AdditionalValidation mAdditionalValidation;

    public StandardFormValidator() {
        this(ValidationStrategy.ALL);
    }

    public StandardFormValidator(ValidationStrategy validationStrategy) {
        mValidationStrategy = validationStrategy;
    }

    public <T> void addFieldValidator(Field<T> field, FieldValidator<T> validator) {
        if (mFieldValidators.containsKey(field)) {
            throw new IllegalArgumentException("StandardFormValidator: more than one validator for field " + field.getName());
        }
	    mFieldValidators.put(field, validator);
    }

    public ValidationStrategy getValidationStrategy() {
        return mValidationStrategy;
    }

    public void setValidationStrategy(ValidationStrategy validationStrategy) {
        mValidationStrategy = validationStrategy;
    }

    public void setAdditionalValidation(AdditionalValidation additionalValidation) {
        mAdditionalValidation = additionalValidation;
    }

    @SuppressWarnings("unchecked")
    public FormValidationResult validate(Form form) {
        // check that all fields exists
        for (Map.Entry<Field, FieldValidator> entry : mFieldValidators.entrySet()) {
            Field field = entry.getKey();
            FieldValidator fieldValidator = entry.getValue();
            if(fieldValidator != null && !form.containsField(field)) {
                throw new IllegalArgumentException("StandardFormValidator: form does not contain field " + field.getName());
            }
        }

        FormValidationResult result = new FormValidationResult();
        for (Field field: form.getFields()) {
            FieldValidator fieldValidator = mFieldValidators.get(field);
            FieldValidationResult fieldValidationResult = fieldValidator != null ? fieldValidator.validate(form, field) : FieldValidationResult.VALID;
            result.putFieldValidationResult(field, fieldValidationResult);
            if(mValidationStrategy == ValidationStrategy.UNTIL_FIRST_ERROR && !fieldValidationResult.isValid()) {
                break;
            }
        }

        if(mAdditionalValidation != null) {
            mAdditionalValidation.call(result);
        }

        return result;
    }

    @SuppressWarnings("unchecked")
    public FieldValidationResult validateField(Form form, Field field) {
        if(!form.containsField(field)) {
            throw new IllegalArgumentException("StandardFormValidator: form does not contain field " + field.getName());
        }

        FieldValidator fieldValidator = mFieldValidators.get(field);
        if(fieldValidator == null) {
            return FieldValidationResult.VALID;
        }
        return fieldValidator.validate(form, field);
    }

    public interface AdditionalValidation {
        void call(FormValidationResult formValidationResult);
    }
}
