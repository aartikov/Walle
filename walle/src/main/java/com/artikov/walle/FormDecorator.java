package com.artikov.walle;

/**
 * Date: 12/11/2016
 * Time: 19:14
 *
 * @author Artur Artikov
 */
abstract public class FormDecorator {
	private OnValidationResultModifiedListener mOnValidationResultModifiedListener;

	public abstract void decorate(FormValidationResult result);

	public abstract void clear();

	public void setOnValidationResultModifiedListener(OnValidationResultModifiedListener onValidationResultModifiedListener) {
		mOnValidationResultModifiedListener = onValidationResultModifiedListener;
	}

	public OnValidationResultModifiedListener getOnValidationResultModifiedListener() {
		return mOnValidationResultModifiedListener;
	}

	protected void notifyThatValidationResultModified(FormValidationResult result) {
		if(mOnValidationResultModifiedListener != null) {
			mOnValidationResultModifiedListener.onModified(result);
		}
	}

	public interface OnValidationResultModifiedListener {
		void onModified(FormValidationResult result);
	}
}