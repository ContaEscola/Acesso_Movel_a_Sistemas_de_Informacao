package com.mv.fp4.ui.static_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mv.fp4.R;

public class StaticBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_book);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}