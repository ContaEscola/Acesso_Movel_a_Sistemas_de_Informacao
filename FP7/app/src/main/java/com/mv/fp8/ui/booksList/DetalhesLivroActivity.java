package com.mv.fp8.ui.booksList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.mv.fp8.R;
import com.mv.fp8.data.db.AppDBManager;
import com.mv.fp8.data.db.model.Book;
import com.mv.fp8.data.db.model.SingletonBookManager;
import com.mv.fp8.databinding.ActivityDetalhesLivroBinding;

public class DetalhesLivroActivity extends AppCompatActivity {

    public static final String BOOK_ID = "book_id";

    public static final String SCENARIO_KEY = "SCENARIO_KEY";
    public static final String SCENARIO_UPDATE = "SCENARIO_UPDATE";
    public static final String SCENARIO_ADD = "SCENARIO_ADD";

    public static final String RESULT_MESSAGE = "RESULT_MESSAGE";


    private String currentScenario;

    private ActivityDetalhesLivroBinding binding;
    private Book book;

    private void init() {

        currentScenario = getIntent().getStringExtra(SCENARIO_KEY);

        switch (currentScenario){
            case SCENARIO_UPDATE:
                int bookId = getIntent().getIntExtra(BOOK_ID, -1);
                book = SingletonBookManager.getInstance(this).getBook(bookId);

                setTitle("Detalhes: " + book.getTitle());

                binding.BookDetailsIvCoverPlaceholder.setImageResource(book.getCover());
                binding.BookDetailsEtTitlePlaceholder.setText(book.getTitle());
                binding.BookDetailsEtSeriePlaceholder.setText(book.getSerie());
                binding.BookDetailsEtAuthorPlaceholder.setText(book.getAuthor());
                binding.BookDetailsEtYearPlaceholder.setText(String.valueOf(book.getYear()));
                break;

            case SCENARIO_ADD:
                setTitle("Adicionar um novo livro");

                binding.BooksDetailsActFloatBtnAction.setImageResource(R.drawable.ic_action_add);

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDetalhesLivroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        init();

        binding.BooksDetailsActFloatBtnAction.setOnClickListener(view -> {
            switch (currentScenario) {
                case SCENARIO_UPDATE:
                    SingletonBookManager.getInstance(this).editBookDB(getBookFromForm());
                    returnResult(RESULT_OK, "Livro modificado com successo!");
                    break;

                case SCENARIO_ADD:
                    SingletonBookManager.getInstance(this).addBookDB(getBookFromForm());
                    returnResult(RESULT_OK, "Livro adicionado com successo!");
                    break;

            }
        });

    }

    private Book getBookFromForm() {
        Book bookFromForm = new Book();
        bookFromForm.setTitle(binding.BookDetailsEtTitlePlaceholder.getText().toString());
        bookFromForm.setAuthor(binding.BookDetailsEtAuthorPlaceholder.getText().toString());
        bookFromForm.setSerie(binding.BookDetailsEtSeriePlaceholder.getText().toString());

        if(binding.BookDetailsEtYearPlaceholder.getText().toString().isEmpty())
                bookFromForm.setYear(0);
        else
                bookFromForm.setYear(Integer.valueOf(binding.BookDetailsEtYearPlaceholder.getText().toString()));

        switch(currentScenario){
            case SCENARIO_ADD:
                bookFromForm.setId(AppDBManager.getInstance(this).getLastBookIdDB() + 1);
                bookFromForm.setCover(R.drawable.img_book_cover_1);
                break;
            case SCENARIO_UPDATE:
                bookFromForm.setId(book.getId());
                bookFromForm.setCover(book.getCover());
                break;

        }

        return bookFromForm;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(currentScenario.equals(SCENARIO_UPDATE)) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_detalhes_livro, menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.MenuBookDetails_ItemDelete:
                deleteBook();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void returnResult(int resultCode, String resultMessage) {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(RESULT_MESSAGE, resultMessage);
        setResult(resultCode, resultIntent);
        finish();
    }


    private void deleteBook() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Remover Livro");
        dialogBuilder.setIcon(R.drawable.ic_action_delete_theme_custom);
        dialogBuilder.setMessage("Pretende mesmo remover o livro?");

        dialogBuilder.setPositiveButton("Sim", (DialogInterface.OnClickListener) (dialog, which) -> {
            SingletonBookManager.getInstance(this).removeBookDB(book.getId());
            finish();
        });

        dialogBuilder.setNegativeButton("Não", (DialogInterface.OnClickListener) (dialog, which) -> {
            dialog.cancel();
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }
}