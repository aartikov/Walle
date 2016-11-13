package com.artikov.walle_sample;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.artikov.walle.FormDecorator;
import com.artikov.walle.FormValidationResult;
import com.artikov.walle.field_decorators.ComplexDecorator;
import com.artikov.walle.field_decorators.EditTextErrorDecorator;
import com.artikov.walle.field_decorators.ViewBackgroundDecorator;
import com.artikov.walle.form_decorators.PerFieldFormDecorator;
import com.artikov.walle.form_validators.PerFieldFormValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 12/11/2016
 * Time: 16:40
 *
 * @author Artur Artikov
 */
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
        mSignUpButton.setOnClickListener(view -> mPresenter.userClickSignUp(createForm()));
        mFormDecorator = createFormDecorator();
    }

    private SignUpForm createForm() {
        SignUpForm form = new SignUpForm();
        form.putValue(SignUpForm.EMAIL, mEmailEditText.getText().toString());
        form.putValue(SignUpForm.PASSWORD, mPasswordEditText.getText().toString());
        form.putValue(SignUpForm.CONFIRM_PASSWORD, mConfirmPasswordEditText.getText().toString());
        return form;
    }

    private FormDecorator createFormDecorator() {
        PerFieldFormDecorator formDecorator = new PerFieldFormDecorator();
        formDecorator.addFieldDecorator(SignUpForm.EMAIL, new EditTextErrorDecorator(mEmailEditText));
        formDecorator.addFieldDecorator(SignUpForm.PASSWORD, new EditTextErrorDecorator(mPasswordEditText));
        formDecorator.addFieldDecorator(SignUpForm.CONFIRM_PASSWORD, new EditTextErrorDecorator(mConfirmPasswordEditText));
        formDecorator.setOnValidationResultModifiedListener(result -> mPresenter.onValidationResultModified(result));
        return formDecorator;
    }

    @Override
    public void setValidationResult(FormValidationResult result) {
        mFormDecorator.setValidationResult(result);
    }

    @Override
    public void showSignedUpMessage() {
        Toast.makeText(this, "Signed up", Toast.LENGTH_LONG).show();
    }
}
