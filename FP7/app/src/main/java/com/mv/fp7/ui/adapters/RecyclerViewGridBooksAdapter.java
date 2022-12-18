package com.mv.fp7.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mv.fp7.R;
import com.mv.fp7.data.model.Book;
import com.mv.fp7.data.model.SingletonBookManager;
import com.mv.fp7.ui.MenuMainActivity;

import java.util.ArrayList;

/**
 * https://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
 * Resposta Nº3
 */
public class RecyclerViewGridBooksAdapter extends RecyclerView.Adapter<RecyclerViewGridBooksAdapter.BookItem> implements RecyclerViewAdapter {

    private Context context;
    private ArrayList<Book> booksListOriginal;
    private ArrayList<Book> booksList;

    private int getIndexOfBook(Book bookToLookUp) {
        int currentIndex = 0;
        for (Book item: booksList){
            if(item.getId() == bookToLookUp.getId())
                return currentIndex;
            else
                currentIndex++;
        }

        return -1;
    }

    public RecyclerViewGridBooksAdapter(Context context, ArrayList<Book> booksList) {
        this.context = context;
        this.booksListOriginal = booksList;
        this.booksList = new ArrayList<>(booksList);

        SingletonBookManager.getInstance().bindAdapter(this);
    }

    @Override
    public BookItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grelha_livro, parent, false);
        return new BookItem(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewGridBooksAdapter.BookItem holder, int position) {
        Book book = booksList.get(position);
        final int bookId = book.getId();

        holder.update(book);
        holder.itemView.setOnClickListener(view -> {
            ((MenuMainActivity) context).showBookDetails(bookId);
        });
    }

    @Override
    public int getItemCount() {
        return booksList.size();
    }


    @Override
    public void removeBook(Book bookRemoved) {
        int indexOfBook = getIndexOfBook(bookRemoved);
        booksList.remove(indexOfBook);
        notifyItemRemoved(indexOfBook);
    }

    @Override
    public void notifyBookChanged(Book updatedBook) {
        int indexOfBook = getIndexOfBook(updatedBook);
        booksList.set(indexOfBook, updatedBook);
        notifyItemChanged(indexOfBook);
    }

    @Override
    public void notifyBookInserted(Book addedBook) {
        int insertIndex = booksListOriginal.size() - 1;
        booksList.add(addedBook);
        notifyItemInserted(insertIndex);

    }

    public static class BookItem extends RecyclerView.ViewHolder {

        private ImageView cover;

        public BookItem(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.BooksGridItem_Iv_CoverPlaceholder);
        }

        public void update(Book book) {
            cover.setImageResource(book.getCover());
        }

    }
}
