package com.art.wallesample2;

import android.util.Patterns;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.artikov.walle.FieldValidationResult;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.validators.field.CompareValidator;
import com.artikov.walle.validators.field.ComplexValidator;
import com.artikov.walle.validators.field.NotEmptyStringValidator;
import com.artikov.walle.validators.field.PatternValidator;
import com.artikov.walle.validators.form.StandardFormValidator;

/**
 * Date: 12/11/2016
 * Time: 16:45
 *
 * @author Artur Artikov
 */
@InjectViewState
public class SignUpPresenter extends MvpPresenter<SignUpView> {
	private StandardFormValidator mFormValidator;
	private FormValidationResult mValidationResult = new FormValidationResult();

	public SignUpPresenter() {
		mFormValidator = createFormValidator();
	}

	public void userClickSignUp(SignUpForm form) {
		mValidationResult = mFormValidator.validate(form);
		getViewState().setValidationResult(mValidationResult);

		if (mValidationResult.isValid()) {
			getViewState().showSignedUpMessage();
		}
	}

	public void validatePasswords(SignUpForm form) {
		boolean passwordValid = mFormValidator.validateField(form, SignUpForm.PASSWORD).isValid();
		boolean confirmPasswordValid = mFormValidator.validateField(form, SignUpForm.CONFIRM_PASSWORD).isValid();
		if(passwordValid && confirmPasswordValid) {
			mValidationResult.putFieldValidationResult(SignUpForm.PASSWORD, FieldValidationResult.VALID);
			mValidationResult.putFieldValidationResult(SignUpForm.CONFIRM_PASSWORD, FieldValidationResult.VALID);
		} else {
			mValidationResult.removeFieldValidationResult(SignUpForm.PASSWORD);
			mValidationResult.removeFieldValidationResult(SignUpForm.CONFIRM_PASSWORD);
		}
		getViewState().setValidationResult(mValidationResult);
	}

	public void onValidationResultModified(FormValidationResult result) {
		mValidationResult = result;
		getViewState().setValidationResult(mValidationResult);
	}

	private StandardFormValidator createFormValidator() {
		NotEmptyStringValidator notEmptyStringValidator = new NotEmptyStringValidator(R.string.empty_field_error);
		PatternValidator emailValidator = new PatternValidator(Patterns.EMAIL_ADDRESS, R.string.invalid_email_error);
		CompareValidator<String> comparePasswordsValidator = new CompareValidator<>(SignUpForm.PASSWORD, R.string.confirm_password_error);

		StandardFormValidator formValidator = new StandardFormValidator();
		formValidator.addFieldValidator(SignUpForm.EMAIL, new ComplexValidator<>(notEmptyStringValidator, emailValidator));
		formValidator.addFieldValidator(SignUpForm.PASSWORD, notEmptyStringValidator);
		formValidator.addFieldValidator(SignUpForm.CONFIRM_PASSWORD, new ComplexValidator<>(notEmptyStringValidator, comparePasswordsValidator));

		formValidator.setAdditionalValidation(formValidationResult -> {
			FieldValidationResult passwordValidationResult = formValidationResult.getFieldValidationResult(SignUpForm.PASSWORD);
			FieldValidationResult confirmPasswordValidationResult = formValidationResult.getFieldValidationResult(SignUpForm.CONFIRM_PASSWORD);

			if(passwordValidationResult.isValid() && !confirmPasswordValidationResult.isValid()) {
				formValidationResult.removeFieldValidationResult(SignUpForm.PASSWORD);
			}
		});
		return formValidator;
	}
}
