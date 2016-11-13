package com.artikov.walle;

import android.content.Context;

/**
 * Date: 12/11/2016
 * Time: 17:18
 *
 * @author Artur Artikov
 */
public class FieldValidationResult {
    public static final FieldValidationResult VALID = new FieldValidationResult(true, "");

    private boolean mValid;
    private int mMessageResId;
    private String mMessageString;

    public FieldValidationResult(boolean valid, int messageResId) {
        mValid = valid;
        mMessageResId = messageResId;
        mMessageString = "";
    }

    public FieldValidationResult(boolean valid, String messageString) {
        mValid = valid;
        mMessageResId = -1;
        mMessageString = messageString;
    }

    public boolean isValid() {
        return mValid;
    }

    public String getMessage(Context context) {
        return mMessageResId != -1 ? context.getString(mMessageResId) : mMessageString;
    }
}