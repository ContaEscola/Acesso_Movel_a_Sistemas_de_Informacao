package com.mv.fp7.ui.booksList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mv.fp7.R;
import com.mv.fp7.data.model.Book;
import com.mv.fp7.data.model.SingletonBookManager;
import com.mv.fp7.databinding.ActivityDetalhesLivroBinding;

public class DetalhesLivroActivity extends AppCompatActivity {

    public static final String BOOK_ID = "book_id";

    private ActivityDetalhesLivroBinding binding;
    private Book book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalhesLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int bookId = getIntent().getIntExtra(BOOK_ID, -1);
        book = SingletonBookManager.getInstance().getBook(bookId);

        setTitle("Detalhes: " + book.getTitle());

        binding.BookDetailsIvCoverPlaceholder.setImageResource(book.getCover());
        binding.BookDetailsEtTitlePlaceholder.setText(book.getTitle());
        binding.BookDetailsEtSeriePlaceholder.setText(book.getSerie());
        binding.BookDetailsEtAuthorPlaceholder.setText(book.getAuthor());
        binding.BookDetailsEtYearPlaceholder.setText(String.valueOf(book.getYear()));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhes_livro, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MenuBookDetails_ItemDelete:
                SingletonBookManager.getInstance().removeBook(book.getId());
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}