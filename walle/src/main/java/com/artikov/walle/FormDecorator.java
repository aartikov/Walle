package com.artikov.walle;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 12/11/2016
 * Time: 19:14
 *
 * @author Artur Artikov
 */
public class FormDecorator {
    private Map<Field, FieldDecorator> mFieldDecorators = new HashMap<>();
    private HideErrorListener mHideErrorListener;

    public void addFieldDecorator(final Field field, FieldDecorator decorator) {
        if (mFieldDecorators.containsKey(field)) {
            throw new IllegalArgumentException("FormDecorator: more than one decorator for field " + field.getName());
        }

        decorator.setHideErrorListener(new FieldDecorator.HideErrorListener() {
            @Override
            public void hideError() {
                if (mHideErrorListener != null) {
                    mHideErrorListener.hideError(field);
                }
            }
        });

        mFieldDecorators.put(field, decorator);
    }

    public void setHideErrorListener(HideErrorListener hideErrorListener) {
        mHideErrorListener = hideErrorListener;
    }

    public void setValidationResult(FormValidationResult result) {
        for (FieldValidationResult fieldValidationResult : result.getFieldValidationResults()) {
            Field field = fieldValidationResult.getField();
            FieldDecorator decorator = mFieldDecorators.get(field);
            if (decorator == null) {
                throw new IllegalArgumentException("FormDecorator: there is no decorator for field " + field.getName());
            }

            decorator.setValidationResult(fieldValidationResult);
        }
    }

    public interface HideErrorListener {
        void hideError(Field field);
    }
}