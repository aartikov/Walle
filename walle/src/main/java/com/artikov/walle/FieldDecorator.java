package com.artikov.walle;

/**
 * Date: 12/11/2016
 * Time: 18:24
 *
 * @author Artur Artikov
 */
public abstract class FieldDecorator {
	private OnValidationResultResetListener mOnValidationResultResetListener;

	public abstract void decorate(FieldValidationResult result);

	public abstract void clear();

	public void setOnValidationResultResetListener(OnValidationResultResetListener onValidationResultResetListener) {
		mOnValidationResultResetListener = onValidationResultResetListener;
	}

	public OnValidationResultResetListener getOnValidationResultResetListener() {
		return mOnValidationResultResetListener;
	}

	protected void resetValidationResult() {
		if(mOnValidationResultResetListener != null) {
			mOnValidationResultResetListener.onReset();
		}
		clear();
	}

	public interface OnValidationResultResetListener {
		void onReset();
	}
}
