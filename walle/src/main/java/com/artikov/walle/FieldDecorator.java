package com.artikov.walle;

/**
 * Date: 12/11/2016
 * Time: 18:24
 *
 * @author Artur Artikov
 */
public abstract class FieldDecorator {
    private HideErrorListener mHideErrorListener;

    public abstract void setValidationResult(FieldValidationResult result);

    public void setHideErrorListener(HideErrorListener hideErrorListener) {
        mHideErrorListener = hideErrorListener;
    }

    protected void hideError() {
        if (mHideErrorListener != null) {
            mHideErrorListener.hideError();
        }
    }

    public interface HideErrorListener {
        void hideError();
    }
}
