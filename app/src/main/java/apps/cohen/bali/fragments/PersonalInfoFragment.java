package apps.cohen.bali.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.common.SignInButton;

import apps.cohen.bali.R;
import apps.cohen.bali.activities.ActivityMain;
import apps.cohen.bali.adapters.EmailAdapter;
import apps.cohen.bali.login.FacebookLogin;
import apps.cohen.bali.login.GooglePlusLogin;
import butterknife.ButterKnife;


public class PersonalInfoFragment extends Fragment implements FacebookLogin.Listener, GooglePlusLogin.Listener {
    AutoCompleteTextView mEmailView;
    EditText mFirstNameView;
    EditText mLastNameView;
    EditText mPhoneNumber;

    LoginButton mFacebookLoginButton;
    SignInButton mGoogleLoginButton;

    private FacebookLogin mFacebookLogin;
    private GooglePlusLogin mGooglePlusLogin;

    public static PersonalInfoFragment newInstance() {
        PersonalInfoFragment fragment = new PersonalInfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_booking_personal, container, false);
        injectViews(view);

        mEmailView.setAdapter(new EmailAdapter(getActivity()));

//
//        mEmailView.setText(pageData.getString(PersonalInfoPage.DATA_EMAIL));
//        mFirstNameView.setText(pageData.getString(PersonalInfoPage.DATA_FIRST_NAME));
//        mLastNameView.setText(pageData.getString(PersonalInfoPage.DATA_LAST_NAME));
//        mPhoneNumber.setText(pageData.getString(PersonalInfoPage.DATA_PHONE));

        // Callback registration
        mFacebookLogin = new FacebookLogin(this, mFacebookLoginButton, this);

        mGooglePlusLogin = ((ActivityMain) getActivity()).getGooglePlusLogin();
        mGoogleLoginButton.setOnClickListener(mGooglePlusLogin);
        mGooglePlusLogin.setListener(this);

        return view;
    }

    private void injectViews(View view) {
        mEmailView = ButterKnife.findById(view, R.id.email);
        mFirstNameView = ButterKnife.findById(view, R.id.first_name);
        mLastNameView = ButterKnife.findById(view, R.id.last_name);
        mPhoneNumber = ButterKnife.findById(view, R.id.phone_number);

//        mFacebookLoginButton = ButterKnife.findById(view, R.id.facebook_login);
        mGoogleLoginButton = ButterKnife.findById(view, R.id.google_login);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookLogin.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

//    @Override
//    public String getPageKey() {
//        return PersonalInfoPage.KEY;
//    }
//
//    @Override
//    protected void onPageDataSave(Bundle pageData) {
//        pageData.putString(PersonalInfoPage.DATA_EMAIL, mEmailView.getText().toString());
//        pageData.putString(PersonalInfoPage.DATA_FIRST_NAME, mFirstNameView.getText().toString());
//        pageData.putString(PersonalInfoPage.DATA_LAST_NAME, mLastNameView.getText().toString());
//        pageData.putString(PersonalInfoPage.DATA_PHONE, mPhoneNumber.getText().toString());
//    }

    @Override
    public void onFacebookLogin(String email, String firstName, String lastName) {
        mEmailView.setText(email);
        mFirstNameView.setText(firstName);
        mLastNameView.setText(lastName);
    }

    @Override
    public void onGooglePlusLogin(String email, String firstName, String lastName) {
        mEmailView.setText(email);
        mFirstNameView.setText(firstName);
        mLastNameView.setText(lastName);
    }
}
