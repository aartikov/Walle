package com.artikov.walle.decorators.form;

import java.util.HashMap;
import java.util.Map;

import com.artikov.walle.Field;
import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FormDecorator;
import com.artikov.walle.FormValidationResult;

/**
 * Date: 13/11/2016
 * Time: 19:09
 *
 * @author Artur Artikov
 */
public class PerFieldFormDecorator extends FormDecorator {
	private Map<Field, FieldDecorator> mFieldDecorators = new HashMap<>();
	private FormValidationResult mFormValidationResult;
	private AdditionalDecoration mAdditionalDecoration;
	private AdditionalClearing mAdditionalClearing;

	public void addFieldDecorator(final Field field, FieldDecorator decorator) {
		if (mFieldDecorators.containsKey(field)) {
			throw new IllegalArgumentException("PerFieldFormDecorator: more than one decorator for field " + field.getName());
		}

		decorator.setOnValidationResultResetListener(new FieldDecorator.OnValidationResultResetListener() {
			@Override
			public void onReset() {
				if (mFormValidationResult != null) {
					mFormValidationResult.removeFieldValidationResult(field);
					notifyThatValidationResultModified(mFormValidationResult);
				}
			}
		});

		mFieldDecorators.put(field, decorator);
	}

	public void setAdditionalDecoration(AdditionalDecoration additionalDecoration) {
		mAdditionalDecoration = additionalDecoration;
	}

	public void setAdditionalClearing(AdditionalClearing additionalClearing) {
		mAdditionalClearing = additionalClearing;
	}

	@Override
	public void decorate(FormValidationResult result) {
		mFormValidationResult = result;
		for (Map.Entry<Field, FieldDecorator> entry : mFieldDecorators.entrySet()) {
			FieldDecorator decorator = entry.getValue();
			FieldValidationResult fieldValidationResult = result.getFieldValidationResult(entry.getKey());
			if(fieldValidationResult != null) {
				decorator.decorate(fieldValidationResult);
			} else {
				decorator.clear();
			}
		}

		if(mAdditionalDecoration != null) {
			mAdditionalDecoration.call(mFormValidationResult);
		}
	}

	@Override
	public void clear() {
		mFormValidationResult = null;
		for (FieldDecorator decorator: mFieldDecorators.values()) {
			decorator.clear();
		}

		if(mAdditionalClearing != null) {
			mAdditionalClearing.call();
		}
	}

	public interface AdditionalDecoration {
		void call(FormValidationResult formValidationResult);
	}

	public interface AdditionalClearing {
		void call();
	}
}

