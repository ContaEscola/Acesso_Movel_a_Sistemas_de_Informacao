package com.mv.fp3.ui.login;



import com.mv.fp3.R;
import com.mv.fp3.data.model.User;

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
