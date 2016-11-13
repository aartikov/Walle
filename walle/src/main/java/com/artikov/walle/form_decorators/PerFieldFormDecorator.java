package com.artikov.walle.form_decorators;

import com.artikov.walle.Field;
import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FormDecorator;
import com.artikov.walle.FormValidationResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Date: 13/11/2016
 * Time: 19:09
 *
 * @author Artur Artikov
 */
public class PerFieldFormDecorator extends FormDecorator {
    private Map<Field, FieldDecorator> mFieldDecorators = new HashMap<>();

    public void addFieldDecorator(final Field field, FieldDecorator decorator) {
        if (mFieldDecorators.containsKey(field)) {
            throw new IllegalArgumentException("FormDecorator: more than one decorator for field " + field.getName());
        }

        decorator.setOnValidationResultModifiedListener(new FieldDecorator.OnValidationResultModifiedListener() {
            @Override
            public void onModified(FieldValidationResult fieldValidationResult) {
                FormValidationResult validationResult = getValidationResult();
                if(validationResult != null && validationResult.containsFieldValidationResult(field)) {
                    validationResult.putFieldValidationResult(field, fieldValidationResult);
                    modifyValidationResult(validationResult);
                }
            }
        });

        mFieldDecorators.put(field, decorator);
    }

    @Override
    protected void decorate(FormValidationResult result) {
        for (Field field : result.getFields()) {
            FieldValidationResult fieldValidationResult = result.getFieldValidationResult(field);

            FieldDecorator decorator = mFieldDecorators.get(field);
            if (decorator == null) {
                throw new IllegalArgumentException("PerFieldFormDecorator: there is no decorator for field " + field.getName());
            }

            decorator.setValidationResult(fieldValidationResult);
        }
    }
}

