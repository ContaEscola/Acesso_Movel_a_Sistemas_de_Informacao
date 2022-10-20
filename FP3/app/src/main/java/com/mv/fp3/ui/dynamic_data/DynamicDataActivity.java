package com.mv.fp3.ui.dynamic_data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.mv.fp3.R;
import com.mv.fp3.data.model.Book;
import com.mv.fp3.data.model.SingletonBookManager;

public class DynamicDataActivity extends AppCompatActivity {

    private TextView titlePlaceholder;
    private TextView seriePlaceholder;
    private TextView authorPlaceholder;
    private TextView yearPlaceholder;
    private ImageView coverPlaceholder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_data);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bindViews();

    }

    @Override
    protected void onStart() {
        super.onStart();

        Book dynamicBook = SingletonBookManager.getInstance().getBookList().get(0);
        titlePlaceholder.setText(dynamicBook.getTitle());
        seriePlaceholder.setText(dynamicBook.getSerie());
        authorPlaceholder.setText(dynamicBook.getAuthor());
        yearPlaceholder.setText(dynamicBook.getYear());
        coverPlaceholder.setImageResource(dynamicBook.getCover());
    }

    private void bindViews() {
        titlePlaceholder = findViewById(R.id.DynamicDataAct_Tv_TitlePlaceholder);
        seriePlaceholder = findViewById(R.id.DynamicDataAct_Tv_SeriePlaceholder);
        authorPlaceholder = findViewById(R.id.DynamicDataAct_Tv_AutorPlaceholder);
        yearPlaceholder = findViewById(R.id.DynamicDataAct_Tv_AnoPlaceholder);
        coverPlaceholder = findViewById(R.id.DynamicDataAct_Iv_BookCoverPlaceholder);
    }
}