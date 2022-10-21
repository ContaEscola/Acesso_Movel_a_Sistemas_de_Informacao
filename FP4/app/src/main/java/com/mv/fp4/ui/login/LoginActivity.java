package com.mv.fp4.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mv.fp4.R;
import com.mv.fp4.ui.home.HomeActivity;

public class LoginActivity extends AppCompatActivity {

    private final LoginController mController = new LoginController(this);

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
                mController.onLogin(email,password);
            else
                Toast.makeText(this, "O formato do email ou password está errado!", Toast.LENGTH_SHORT).show();

        });
    }

    private void init() {
        etEmail = findViewById(R.id.LoginAct_Et_Email);
        etPassword = findViewById(R.id.LoginAct_Et_Password);

        btnLogin = findViewById(R.id.LoginAct_Btn_Login);
    }

    public void onLoginSuccess(){
        Intent homeActivity = new Intent(getApplicationContext(), HomeActivity.class);
        startActivity(homeActivity);
    }

    public void onLoginError() {
        Toast.makeText(this, getString(R.string.ON_LOGIN_ERROR), Toast.LENGTH_SHORT).show();
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
}