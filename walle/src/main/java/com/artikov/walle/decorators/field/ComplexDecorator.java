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
			decorator.setOnValidationResultResetListener(new OnValidationResultResetListener() {
				@Override
				public void onReset() {
					resetValidationResult();
				}
			});
		}
	}

	@Override
	public void decorate(FieldValidationResult result) {
		for (FieldDecorator decorator : mDecorators) {
			decorator.decorate(result);
		}
	}

	@Override
	public void clear() {
		for (FieldDecorator decorator : mDecorators) {
			decorator.clear();
		}
	}
}
