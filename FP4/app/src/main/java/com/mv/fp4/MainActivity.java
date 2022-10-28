package com.mv.fp4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.mv.fp4.views.MenuMainActivity;

public class MainActivity extends AppCompatActivity {


    private Button btnShowMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnShowMenu = findViewById(R.id.btnShowMenu);

        btnShowMenu.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MenuMainActivity.class);
            intent.putExtra(MenuMainActivity.MAIL,"testing@gmail.com");
            startActivity(intent);
        });
    }
}