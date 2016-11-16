package com.artikov.wallesample;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.FormValidator;
import com.artikov.walle.validators.field.CompareValidator;
import com.artikov.walle.validators.field.ComplexValidator;
import com.artikov.walle.validators.field.NotEmptyStringValidator;
import com.artikov.walle.validators.field.PatternValidator;
import com.artikov.walle.validators.form.PerFieldFormValidator;
import com.artikov.wallesample.validators.EmailValidator;
import com.artikov.wallesample.validators.PasswordValidator;

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

	public void onValidationResultModified(FormValidationResult result) {
		getViewState().setValidationResult(result);
	}

	private FormValidator createFormValidator() {
		NotEmptyStringValidator notEmptyStringValidator = new NotEmptyStringValidator(R.string.empty_field_error);
		PatternValidator emailValidator = new EmailValidator(R.string.invalid_email_error);
		PasswordValidator passwordValidator = new PasswordValidator(R.string.invalid_password_error);
		CompareValidator<String> comparePasswordsValidator = new CompareValidator<>(SignUpForm.PASSWORD, R.string.confirm_password_error);

		PerFieldFormValidator formValidator = new PerFieldFormValidator();
		formValidator.addFieldValidator(SignUpForm.EMAIL, new ComplexValidator<>(notEmptyStringValidator, emailValidator));
		formValidator.addFieldValidator(SignUpForm.PASSWORD, new ComplexValidator<>(notEmptyStringValidator, passwordValidator));
		formValidator.addFieldValidator(SignUpForm.CONFIRM_PASSWORD, new ComplexValidator<>(notEmptyStringValidator, comparePasswordsValidator));
		return formValidator;
	}
}
