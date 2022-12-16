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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

/**
 * https://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
 * Resposta Nº3
 */
public class RecyclerViewBooksAdapter extends RecyclerView.Adapter<RecyclerViewBooksAdapter.BookItem> implements Filterable {

    private Context context;
    private ArrayList<Book> booksListOriginal;
    private ArrayList<Book> booksList;

    public RecyclerViewBooksAdapter(Context context, ArrayList<Book> booksList) {
        this.context = context;
        this.booksListOriginal = booksList;
        this.booksList = booksList;

        SingletonBookManager.getInstance().bindAdapter(this);
    }

    @Override
    public BookItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list_item, parent, false);
        return new BookItem(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewBooksAdapter.BookItem holder, int position) {
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
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                ArrayList<Book> filteredList = null;
                if(charSequence.length() == 0)
                    filteredList = booksListOriginal;
                else
                    filteredList = getFilteredList(charSequence.toString().toLowerCase());

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                booksList = (ArrayList<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private ArrayList<Book> getFilteredList(String query) {
        ArrayList<Book> results = new ArrayList<>();

        for(Book item: booksListOriginal) {
            if(item.getTitle().toLowerCase().contains(query))
                results.add(item);
        }

        return results;
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
