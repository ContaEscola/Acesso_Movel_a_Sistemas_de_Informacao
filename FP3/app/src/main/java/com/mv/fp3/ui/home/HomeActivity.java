package com.mv.fp3.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.mv.fp3.R;
import com.mv.fp3.data.model.User;
import com.mv.fp3.ui.dynamic_data.DynamicDataActivity;
import com.mv.fp3.ui.static_data.StaticBookActivity;


public class HomeActivity extends AppCompatActivity {

    private TextView tvEmailPlaceholder;

    private Button btnGoToStaticBook;
    private Button btnGoToDynamicBook;
    private Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bindViews();

        tvEmailPlaceholder.setText(User.getEmail());

        btnGoToStaticBook.setOnClickListener(view -> {
            Intent staticBookAct = new Intent(this, StaticBookActivity.class);
            startActivity(staticBookAct);
        });

        btnGoToDynamicBook.setOnClickListener(view -> {
            Intent dynamicBookAct = new Intent(this, DynamicDataActivity.class);
            startActivity(dynamicBookAct);
        });

        btnSendEmail.setOnClickListener(view -> {
            Intent sendEmail = new Intent(Intent.ACTION_SENDTO);
            sendEmail.setData(Uri.parse("mailto: testing@gmail.com"));
            sendEmail.putExtra(Intent.EXTRA_SUBJECT, "PSI - AMSI 2022/2023");
            startActivity(sendEmail);

        });

    }

    private void bindViews(){
        tvEmailPlaceholder = findViewById(R.id.HomeAct_Tv_EmailPlaceholder);

        btnGoToStaticBook = findViewById(R.id.HomeAct_Btn_GoToStaticBook);
        btnGoToDynamicBook = findViewById(R.id.HomeAct_Btn_GoToDynamicBook);
        btnSendEmail = findViewById(R.id.HomeAct_Btn_SendEmail);
    }
}