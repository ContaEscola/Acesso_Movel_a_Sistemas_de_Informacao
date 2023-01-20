package com.mv.fp9.ui.adapters;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mv.fp9.R;
import com.mv.fp9.data.db.model.Book;
import com.mv.fp9.data.db.model.SingletonBookManager;
import com.mv.fp9.ui.MenuMainActivity;

import java.util.LinkedList;

/**
 * https://stackoverflow.com/questions/30398247/how-to-filter-a-recyclerview-with-a-searchview
 * Resposta Nº3
 */
public class RecyclerViewBooksAdapter extends RecyclerView.Adapter<RecyclerViewBooksAdapter.BookItem> implements Filterable, RecyclerViewAdapter {

    protected Context context;
    private LinkedList<Book> booksListOriginal;
    private LinkedList<Book> booksList;

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


    public RecyclerViewBooksAdapter(Context context, LinkedList<Book> booksList) {
        this.context = context;
        this.booksListOriginal = booksList;
        this.booksList = new LinkedList<>(booksList);

        SingletonBookManager.getInstance(context).bindAdapter(this);
    }

    @Override
    public BookItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.books_list_item, parent, false);
        return new BookItem(item, context);
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
                LinkedList<Book> filteredList = null;
                if(charSequence.length() == 0)
                    filteredList = new LinkedList<>(booksListOriginal);
                else
                    filteredList = getFilteredList(charSequence.toString().toLowerCase());

                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                booksList = (LinkedList<Book>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    private LinkedList<Book> getFilteredList(String query) {
        LinkedList<Book> results = new LinkedList<>();

        for(Book item: booksListOriginal) {
            if(item.getTitle().toLowerCase().contains(query))
                results.add(item);
        }

        return results;
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

        private Context context;
        private TextView title;
        private TextView serie;
        private TextView author;
        private TextView year;
        private ImageView cover;

        public BookItem(@NonNull View itemView, Context context) {
            super(itemView);
            this.context = context;
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
//            cover.setImageResource(book.getCover());
//            Com Glide
            Glide.with(context)
                    .load(book.getCover())
                    .placeholder(R.drawable.ic_politecnico_leiria)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(cover);
        }

    }
}
