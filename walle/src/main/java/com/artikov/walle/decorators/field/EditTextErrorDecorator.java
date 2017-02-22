package com.artikov.walle.decorators.field;

import android.widget.EditText;

import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.utils.SimpleTextWatcher;

/**
 * Date: 12/11/2016
 * Time: 18:34
 *
 * @author Artur Artikov
 */
public class EditTextErrorDecorator extends FieldDecorator {
	private EditText mEditText;

	public EditTextErrorDecorator(EditText editText) {
		mEditText = editText;
		mEditText.addTextChangedListener(new SimpleTextWatcher(mEditText) {
			@Override
			public void onTextChanged() {
				resetValidationResult();
			}
		});
	}

	@Override
	public void decorate(FieldValidationResult result) {
		mEditText.setError(result.isValid() ? null : result.getErrorMessage().getString(mEditText.getContext()));
	}

	@Override
	public void clear() {
		mEditText.setError(null);
	}
}
