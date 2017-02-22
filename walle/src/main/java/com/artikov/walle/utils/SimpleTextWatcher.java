package com.artikov.walle.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

/**
 * Date: 11/18/2016
 * Time: 18:23
 *
 * @author Artur Artikov
 */
public abstract class SimpleTextWatcher implements TextWatcher {
	private EditText mEditText;

	public SimpleTextWatcher(EditText editText) {
		mEditText = editText;
	}

	abstract public void onTextChanged();

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count, int after) {
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		if (mEditText != null && mEditText.getWindowVisibility() == View.VISIBLE) {     // Workaround for a problem that onTextChanged called during onRestoreInstanceState
			onTextChanged();
		}
	}

	@Override
	public void afterTextChanged(Editable s) {
	}
}
