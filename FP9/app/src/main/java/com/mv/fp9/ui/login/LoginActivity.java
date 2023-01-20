package com.mv.fp9.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mv.fp9.R;
import com.mv.fp9.data.network.AppApiHelper;
import com.mv.fp9.data.prefs.PreferencesHelper;
import com.mv.fp9.listeners.LoginListener;
import com.mv.fp9.ui.MenuMainActivity;

public class LoginActivity extends AppCompatActivity implements LoginListener {

    private EditText etEmail;
    private EditText etPassword;

    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();


        btnLogin.setOnClickListener(view -> {
            String email = etEmail.getText().toString();
            String password = etPassword.getText().toString();
            if(isEmailValid(email) && isPasswordValid(password))
               AppApiHelper.getInstance(this).authLoginApi(email, password);
            else
                Toast.makeText(this, "O formato do email ou password está errado!", Toast.LENGTH_SHORT).show();

        });


    }

    private void init() {
        AppApiHelper.getInstance(this).setLoginListener(this);

        etEmail = findViewById(R.id.LoginAct_Et_Email);
        etPassword = findViewById(R.id.LoginAct_Et_Password);
        btnLogin = findViewById(R.id.LoginAct_Btn_Login);

        etEmail.setText("aulas@amsi.pt");
        etPassword.setText("aulas-amsi");
    }


    private boolean isEmailValid(String email) {
        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches())
            return true;

        return false;
    }

    private boolean isPasswordValid(String password) {
        if(!password.isEmpty())
            return true;

        return false;
    }

    @Override
    public void onInvalidLogin(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidLogin(String token, String email, Context context) {
        PreferencesHelper.getInstance(this).setTokenPreference(token);
        PreferencesHelper.getInstance(this).setEmailPreference(email);


        Intent menuMainActivity = new Intent(context, MenuMainActivity.class);
        menuMainActivity.putExtra(MenuMainActivity.MAIL, email);
        startActivity(menuMainActivity);

    }
}