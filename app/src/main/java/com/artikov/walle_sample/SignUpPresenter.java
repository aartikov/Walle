package com.artikov.walle_sample;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.artikov.walle.Field;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.FormValidator;
import com.artikov.walle.field_validators.CompareValidator;
import com.artikov.walle.field_validators.ComplexValidator;
import com.artikov.walle.field_validators.NotEmptyStringValidator;
import com.artikov.walle.field_validators.PatternValidator;
import com.artikov.walle_sample.validators.EmailValidator;
import com.artikov.walle_sample.validators.PasswordValidator;

/**
 * Date: 12/11/2016
 * Time: 16:45
 *
 * @author Artur Artikov
 */
@InjectViewState
public class SignUpPresenter extends MvpPresenter<SignUpView> {
    private FormValidator mFormValidator;

    public SignUpPresenter() {
        mFormValidator = createFormValidator();
    }

    public void userClickSignUp(SignUpForm form) {
        FormValidationResult result = mFormValidator.validate(form);
        getViewState().setValidationResult(result);

        if (result.isValid()) {
            getViewState().showSignedUpMessage();
        }
    }

    public void userHideFieldError(Field field) {
        FormValidationResult result = mFormValidator.removeFieldError(field);
        getViewState().setValidationResult(result);
    }

    private FormValidator createFormValidator() {
        NotEmptyStringValidator notEmptyStringValidator = new NotEmptyStringValidator(R.string.empty_field_error);
        PatternValidator emailValidator = new EmailValidator(R.string.invalid_email_error);
        PasswordValidator passwordValidator = new PasswordValidator(R.string.invalid_password_error);
        CompareValidator<String> comparePasswordsValidator = new CompareValidator<>(SignUpForm.PASSWORD, R.string.confirm_password_error);

        FormValidator formValidator = new FormValidator();
        formValidator.addFieldValidator(SignUpForm.EMAIL, new ComplexValidator<>(notEmptyStringValidator, emailValidator));
        formValidator.addFieldValidator(SignUpForm.PASSWORD, new ComplexValidator<>(notEmptyStringValidator, passwordValidator));
        formValidator.addFieldValidator(SignUpForm.CONFIRM_PASSWORD, new ComplexValidator<>(notEmptyStringValidator, comparePasswordsValidator));
        return formValidator;
    }
}
