package com.artikov.walle;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Date: 12/11/2016
 * Time: 23:30
 *
 * @author Artur Artikov
 */
public class FormValidationResult {
	private Map<Field, FieldValidationResult> mFieldValidationResults = new LinkedHashMap<>();

	public FormValidationResult() {
	}

	public boolean isValid() {
		for (FieldValidationResult result : mFieldValidationResults.values()) {
			if (!result.isValid()) {
				return false;
			}
		}
		return true;
	}

	public boolean isFieldValid(Field field) {
		FieldValidationResult fieldValidationResult = getFieldValidationResult(field);
		return fieldValidationResult != null && fieldValidationResult.isValid();
	}

	public void putFieldValidationResult(Field field, FieldValidationResult result) {
		mFieldValidationResults.put(field, result);
	}

	public FieldValidationResult getFieldValidationResult(Field field) {
		return mFieldValidationResults.get(field);
	}

	public void removeFieldValidationResult(Field field) {
		mFieldValidationResults.remove(field);
	}

	public Set<Field> getFields() {
		return mFieldValidationResults.keySet();
	}
}
