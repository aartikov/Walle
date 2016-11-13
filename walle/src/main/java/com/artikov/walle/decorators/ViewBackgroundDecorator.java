package com.artikov.walle.decorators;

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
    @ColorInt private int mValidColor;
    @ColorInt private int mErrorColor;

    public ViewBackgroundDecorator(View view, @ColorInt int validColor, @ColorInt int errorColor) {
        mView = view;
        mValidColor = validColor;
        mErrorColor = errorColor;
    }

    @Override
    public void setValidationResult(FieldValidationResult result) {
        mView.setBackgroundColor(result.isValid() ? mValidColor : mErrorColor);
    }
}
