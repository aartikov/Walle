package com.artikov.walle.decorators.field;

import java.util.Arrays;
import java.util.List;

import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;

/**
 * Date: 12/11/2016
 * Time: 22:18
 *
 * @author Artur Artikov
 */
public class ComplexDecorator extends FieldDecorator {
	private List<FieldDecorator> mDecorators;

	public ComplexDecorator(FieldDecorator... decorators) {
		mDecorators = Arrays.asList(decorators);
		for (FieldDecorator decorator : mDecorators) {
			decorator.setOnValidationResultModifiedListener(new OnValidationResultModifiedListener() {

				@Override
				public void onModified(FieldValidationResult result) {
					setValidationResult(result);
					notifyOnValidationResultModifiedListener(result);
				}
			});
		}
	}

	@Override
	public void setValidationResult(FieldValidationResult result) {
		for (FieldDecorator decorator : mDecorators) {
			decorator.setValidationResult(result);
		}
	}

	@Override
	protected void decorate(FieldValidationResult result) {
		// nothing
	}
}
