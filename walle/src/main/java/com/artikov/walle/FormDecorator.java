package com.artikov.walle;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 12/11/2016
 * Time: 19:14
 *
 * @author Artur Artikov
 */
abstract public class FormDecorator {
    private FormValidationResult mValidationResult;
    private OnValidationResultModifiedListener mOnValidationResultModifiedListener;

    protected abstract void decorate(FormValidationResult result);

    public void setValidationResult(FormValidationResult result) {
        mValidationResult = result;
        decorate(result);
    }

    public FormValidationResult getValidationResult() {
        return mValidationResult;
    }

    public void setOnValidationResultModifiedListener(OnValidationResultModifiedListener onValidationResultModifiedListener) {
        mOnValidationResultModifiedListener = onValidationResultModifiedListener;
    }

    public OnValidationResultModifiedListener getOnValidationResultModifiedListener() {
        return mOnValidationResultModifiedListener;
    }

    protected void modifyValidationResult(FormValidationResult newResult) {
        setValidationResult(newResult);
        if(mOnValidationResultModifiedListener != null) {
            mOnValidationResultModifiedListener.onModified(newResult);
        }
    }

    public interface OnValidationResultModifiedListener {
        void onModified(FormValidationResult result);
    }
}