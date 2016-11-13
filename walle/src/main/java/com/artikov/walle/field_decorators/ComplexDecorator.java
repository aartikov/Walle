package com.artikov.walle.field_decorators;

import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;

import java.util.Arrays;
import java.util.List;

/**
 * Date: 12/11/2016
 * Time: 22:18
 *
 * @author Artur Artikov
 */
public class ComplexDecorator extends FieldDecorator {
    private List<FieldDecorator> mDecorators;

    public ComplexDecorator(FieldDecorator... decorators) {
        mDecorators = Arrays.asList(decorators);
        for (FieldDecorator decorator : mDecorators) {
            decorator.setHideErrorListener(new HideErrorListener() {
                @Override
                public void hideError() {
                    ComplexDecorator.this.hideError();
                }
            });
        }
    }

    @Override
    public void setValidationResult(FieldValidationResult result) {
        for (FieldDecorator decorator : mDecorators) {
            decorator.setValidationResult(result);
        }
    }
}
