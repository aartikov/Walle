package com.artikov.walle;

/**
 * Date: 12/11/2016
 * Time: 18:24
 *
 * @author Artur Artikov
 */
public abstract class FieldDecorator {
    private FieldValidationResult mValidationResult;
    private OnValidationResultModifiedListener mOnValidationResultModifiedListener;

    protected abstract void decorate(FieldValidationResult result);

    public void setValidationResult(FieldValidationResult result) {
        mValidationResult = result;
        decorate(result);
    }

    public FieldValidationResult getValidationResult() {
        return mValidationResult;
    }

    public void setOnValidationResultModifiedListener(OnValidationResultModifiedListener onValidationResultModifiedListener) {
        mOnValidationResultModifiedListener = onValidationResultModifiedListener;
    }

    public OnValidationResultModifiedListener getOnValidationResultModifiedListener() {
        return mOnValidationResultModifiedListener;
    }

    protected void modifyValidationResult(FieldValidationResult newResult) {
        setValidationResult(newResult);
        if(mOnValidationResultModifiedListener != null) {
            mOnValidationResultModifiedListener.onModified(newResult);
        }
    }

    public interface OnValidationResultModifiedListener {
        void onModified(FieldValidationResult result);
    }
}
