package com.artikov.walle;

import android.content.Context;

/**
 * Date: 12/11/2016
 * Time: 17:18
 *
 * @author Artur Artikov
 */
public class FieldValidationResult {
    private Field mField;
    private boolean mValid;
    private int mMessageResId;
    private String mMessageString;
    private Object mCustomData;

    public FieldValidationResult(Field field, boolean valid, int messageResId) {
        mField = field;
        mValid = valid;
        mMessageResId = messageResId;
        mMessageString = "";
    }

    public FieldValidationResult(Field field, boolean valid, String messageString) {
        mField = field;
        mValid = valid;
        mMessageResId = -1;
        mMessageString = messageString;
    }

    static public FieldValidationResult createValid(Field field) {
        return new FieldValidationResult(field, true, "");
    }

    public Field getField() {
        return mField;
    }

    public boolean isValid() {
        return mValid;
    }

    public String getMessage(Context context) {
        return mMessageResId != -1 ? context.getString(mMessageResId) : mMessageString;
    }

    public Object getCustomData() {
        return mCustomData;
    }

    public void setCustomData(Object customData) {
        mCustomData = customData;
    }
}
