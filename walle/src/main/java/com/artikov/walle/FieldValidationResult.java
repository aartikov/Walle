package com.artikov.walle;

import android.content.Context;

/**
 * Date: 12/11/2016
 * Time: 17:18
 *
 * @author Artur Artikov
 */
public class FieldValidationResult {
    public static final FieldValidationResult VALID = new FieldValidationResult(true, StringWrapper.EMPTY);

    private boolean mValid;
    private StringWrapper mErrorMessage;

    public FieldValidationResult(boolean valid, StringWrapper errorMessage) {
        mValid = valid;
        mErrorMessage = errorMessage;
    }

    public boolean isValid() {
        return mValid;
    }

    public StringWrapper getErrorMessage() {
        return mErrorMessage;
    }
}