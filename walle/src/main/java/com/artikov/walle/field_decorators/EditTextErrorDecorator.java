package com.artikov.walle.field_decorators;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.artikov.walle.Field;
import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;

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
        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(getValidationResult() != null) {
                    modifyValidationResult(FieldValidationResult.VALID);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    @Override
    protected void decorate(FieldValidationResult result) {
        mEditText.setError(result.isValid() ? null : result.getErrorMessage().getString(mEditText.getContext()));
    }
}
