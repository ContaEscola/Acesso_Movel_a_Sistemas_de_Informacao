package com.mv.fp6.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mv.fp6.R;
import com.mv.fp6.data.model.Book;
import com.mv.fp6.ui.MenuMainActivity;
import com.mv.fp6.ui.booksList.BooksListFragment;

import java.util.ArrayList;

public class RecyclerViewBooksAdapter extends RecyclerView.Adapter<RecyclerViewBooksAdapter.BookItem> {

    private Context context;
    private ArrayList<Book> booksList;

    public RecyclerViewBooksAdapter(Context context, ArrayList<Book> booksList) {
        this.context = context;
        this.booksList = booksList;
    }

    @Override
    public BookItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType){

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list_item, parent, false);
        return new BookItem(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBooksAdapter.BookItem holder, int position) {
        Book book = booksList.get(position);
        final int posicao = position;

        holder.update(book);
        holder.itemView.setOnClickListener(view -> {
            ((MenuMainActivity) context).showBookDetails(posicao);
        });
    }


    @Override
    public int getItemCount() {
        return booksList.size();
    }

    public class BookItem extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView serie;
        private TextView author;
        private TextView year;
        private ImageView cover;

        public BookItem(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.BooksListItem_Tv_TitlePlaceholder);
            serie = itemView.findViewById(R.id.BooksListItem_Tv_SeriePlaceholder);
            author = itemView.findViewById(R.id.BooksListItem_Tv_AutorPlaceholder);
            year = itemView.findViewById(R.id.BooksListItem_Tv_AnoPlaceholder);
            cover = itemView.findViewById(R.id.BooksListItem_Iv_BookCoverPlaceholder);
        }

        public void update(Book book) {
            title.setText(book.getTitle());
            serie.setText(book.getSerie());
            author.setText(book.getAuthor());
            year.setText(String.valueOf(book.getYear()));
            cover.setImageResource(book.getCover());
        }

    }
}
