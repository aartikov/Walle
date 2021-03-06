package com.artikov.walle.decorators.field;

import android.support.annotation.ColorInt;
import android.view.View;

import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;

/**
 * Date: 12/11/2016
 * Time: 22:07
 *
 * @author Artur Artikov
 */
public class ViewBackgroundDecorator extends FieldDecorator {
	private View mView;
	@ColorInt
	private int mDefaultColor;
	@ColorInt
	private int mValidColor;
	@ColorInt
	private int mErrorColor;

	public ViewBackgroundDecorator(View view, @ColorInt int defaultColor, @ColorInt int validColor, @ColorInt int errorColor) {
		mView = view;
		mDefaultColor = defaultColor;
		mValidColor = validColor;
		mErrorColor = errorColor;
	}

	public ViewBackgroundDecorator(View view, @ColorInt int defaultColor, @ColorInt int errorColor) {
		this(view, defaultColor, defaultColor, errorColor);
	}

	@Override
	public void decorate(FieldValidationResult result) {
		mView.setBackgroundColor(result.isValid() ? mValidColor : mErrorColor);
	}

	@Override
	public void clear() {
		mView.setBackgroundColor(mDefaultColor);
	}
}
