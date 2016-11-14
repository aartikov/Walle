package com.artikov.walle;

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

	protected void notifyOnValidationResultModifiedListener(FormValidationResult result) {
		if(mOnValidationResultModifiedListener != null) {
			mOnValidationResultModifiedListener.onModified(result);
		}
	}

	public interface OnValidationResultModifiedListener {
		void onModified(FormValidationResult result);
	}
}