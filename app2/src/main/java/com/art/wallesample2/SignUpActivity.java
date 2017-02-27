package com.art.wallesample2;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.artikov.walle.FieldDecorator;
import com.artikov.walle.FormDecorator;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.decorators.field.ComplexDecorator;
import com.artikov.walle.decorators.field.EditTextErrorDecorator;
import com.artikov.walle.decorators.field.ViewBackgroundDecorator;
import com.artikov.walle.decorators.form.StandardFormDecorator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends MvpAppCompatActivity implements SignUpView {
	@BindView(R.id.activity_sign_up_edit_text_email)
	EditText mEmailEditText;

	@BindView(R.id.activity_sign_up_edit_text_password)
	EditText mPasswordEditText;

	@BindView(R.id.activity_sign_up_edit_text_confirm_password)
	EditText mConfirmPasswordEditText;

	@BindView(R.id.activity_sign_up_button_sign_up)
	Button mSignUpButton;

	@InjectPresenter
	SignUpPresenter mPresenter;

	private FormDecorator mFormDecorator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		ButterKnife.bind(this);
		mFormDecorator = createFormDecorator();

		mSignUpButton.setOnClickListener(view -> mPresenter.userClickSignUp(createForm()));

		TextWatcher passwordTextWatcher = new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				mPresenter.validatePasswords(createForm());
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		};

		mPasswordEditText.addTextChangedListener(passwordTextWatcher);
		mConfirmPasswordEditText.addTextChangedListener(passwordTextWatcher);
	}

	private SignUpForm createForm() {
		SignUpForm form = new SignUpForm();
		form.putValue(SignUpForm.EMAIL, mEmailEditText.getText().toString());
		form.putValue(SignUpForm.PASSWORD, mPasswordEditText.getText().toString());
		form.putValue(SignUpForm.CONFIRM_PASSWORD, mConfirmPasswordEditText.getText().toString());
		return form;
	}

	private FormDecorator createFormDecorator() {
		StandardFormDecorator formDecorator = new StandardFormDecorator();
		formDecorator.addFieldDecorator(SignUpForm.EMAIL, new EditTextErrorDecorator(mEmailEditText));

		FieldDecorator passwordDecorator = new ComplexDecorator(
				new ViewBackgroundDecorator(mPasswordEditText, Color.TRANSPARENT, Color.GREEN, Color.TRANSPARENT),
				new EditTextErrorDecorator(mPasswordEditText));
		formDecorator.addFieldDecorator(SignUpForm.PASSWORD, passwordDecorator);

		FieldDecorator confirmPasswordDecorator = new ComplexDecorator(
				new ViewBackgroundDecorator(mConfirmPasswordEditText, Color.TRANSPARENT, Color.GREEN, Color.TRANSPARENT),
				new EditTextErrorDecorator(mConfirmPasswordEditText));
		formDecorator.addFieldDecorator(SignUpForm.CONFIRM_PASSWORD, confirmPasswordDecorator);

		formDecorator.setOnValidationResultModifiedListener(result -> mPresenter.onValidationResultModified(result));
		return formDecorator;
	}

	@Override
	public void setValidationResult(FormValidationResult result) {
		mFormDecorator.decorate(result);
	}

	@Override
	public void showSignedUpMessage() {
		Toast.makeText(this, "Signed up", Toast.LENGTH_LONG).show();
	}
}
