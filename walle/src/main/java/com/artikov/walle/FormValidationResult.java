package com.artikov.walle;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 12/11/2016
 * Time: 23:30
 *
 * @author Artur Artikov
 */
public class FormValidationResult {
    private List<FieldValidationResult> mFieldValidationResults = new ArrayList<>();

    public FormValidationResult() {
    }

    public FormValidationResult(List<FieldValidationResult> fieldValidationResults) {
        mFieldValidationResults.addAll(fieldValidationResults);
    }

    public boolean isValid() {
        for (FieldValidationResult validatorResult : mFieldValidationResults) {
            if (!validatorResult.isValid()) {
                return false;
            }
        }
        return true;
    }

    public List<FieldValidationResult> getFieldValidationResults() {
        return mFieldValidationResults;
    }

    public void removeAllErrors() {
        for (int i = 0; i < mFieldValidationResults.size(); i++) {
            FieldValidationResult result = mFieldValidationResults.get(i);
            if (!result.isValid()) {
                mFieldValidationResults.set(i, FieldValidationResult.createValid(result.getField()));
            }
        }
    }

    public void removeFieldError(Field field) {
        for (int i = 0; i < mFieldValidationResults.size(); i++) {
            FieldValidationResult result = mFieldValidationResults.get(i);
            if (!result.isValid() && field.equals(result.getField())) {
                mFieldValidationResults.set(i, FieldValidationResult.createValid(field));
            }
        }
    }
}
