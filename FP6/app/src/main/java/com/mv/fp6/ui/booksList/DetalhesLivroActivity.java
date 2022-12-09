package com.mv.fp6.ui.booksList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.mv.fp6.R;
import com.mv.fp6.data.model.Book;
import com.mv.fp6.data.model.SingletonBookManager;
import com.mv.fp6.databinding.ActivityDetalhesLivroBinding;

public class DetalhesLivroActivity extends AppCompatActivity {

    public static final String BOOK_POSITION = "book_position";

    private ActivityDetalhesLivroBinding binding;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalhesLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int book_position = getIntent().getIntExtra(BOOK_POSITION, -1);
        book = SingletonBookManager.getInstance().getBook(book_position);

        setTitle("Detalhes: " + book.getTitle());

        binding.BookDetailsIvCoverPlaceholder.setImageResource(book.getCover());
        binding.BookDetailsEtTitlePlaceholder.setText(book.getTitle());
        binding.BookDetailsEtSeriePlaceholder.setText(book.getSerie());
        binding.BookDetailsEtAuthorPlaceholder.setText(book.getAuthor());
        binding.BookDetailsEtYearPlaceholder.setText(String.valueOf(book.getYear()));

    }
}