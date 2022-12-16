package com.mv.fp7.ui.login;



import com.mv.fp7.data.model.User;
import com.mv.fp7.data.prefs.PreferencesHelper;

public class LoginController {

    private User mModel;
    private LoginActivity mView;

    public LoginController(LoginActivity view) {
        this.mView = view;
        this.mModel = new User();
    }

    public void onLogin(String email, String password) {
       if(mModel.isValidLogin(email, password))
            mView.onLoginSuccess();
       else
            mView.onLoginError();

    }
}
