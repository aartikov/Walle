package com.artikov.walle;

/**
 * Date: 12/11/2016
 * Time: 17:36
 *
 * @author Artur Artikov
 */
public abstract class FieldValidator<T> {
	public abstract FieldValidationResult validate(Form form, Field<T> field);
}