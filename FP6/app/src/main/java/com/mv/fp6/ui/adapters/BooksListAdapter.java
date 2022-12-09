package com.mv.fp6.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mv.fp6.R;
import com.mv.fp6.data.model.Book;
import com.mv.fp6.data.model.SingletonBookManager;

import java.util.ArrayList;

public class BooksListAdapter extends BaseAdapter {

    private class ViewHolder {
        private TextView title;
        private TextView serie;
        private TextView author;
        private TextView year;
        private ImageView cover;
        private Book book;

        public ViewHolder(View view, Book book) {
            this(view);
            this.book = book;
        }

        public ViewHolder(View view) {
            title = view.findViewById(R.id.BooksListItem_Tv_TitlePlaceholder);
            serie = view.findViewById(R.id.BooksListItem_Tv_SeriePlaceholder);
            author = view.findViewById(R.id.BooksListItem_Tv_AutorPlaceholder);
            year = view.findViewById(R.id.BooksListItem_Tv_AnoPlaceholder);
            cover = view.findViewById(R.id.BooksListItem_Iv_BookCoverPlaceholder);

        }

        public void updateBook() {
            title.setText(book.getTitle());
            serie.setText(book.getSerie());
            author.setText(book.getAuthor());
            year.setText(String.valueOf(book.getYear()));
            cover.setImageResource(book.getCover());
        }

        public void updateBook(Book book) {
            title.setText(book.getTitle());
            serie.setText(book.getSerie());
            author.setText(book.getAuthor());
            year.setText(String.valueOf(book.getYear()));
            cover.setImageResource(book.getCover());
        }


    }

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Book> booksList;

    public BooksListAdapter(Context context, ArrayList<Book> booksList) {
        this.context = context;
        this.booksList = booksList;

    }


    @Override
    public int getCount() {
        return booksList.size();
    }

    @Override
    public Object getItem(int i) {
        return booksList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return booksList.get(i).getId();
    }



    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Book book = booksList.get(i);

        if(layoutInflater == null)
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ViewHolder viewHolder;
        if(view == null)
            //view = layoutInflater.inflate(R.layout.books_list_item, null);
            view = layoutInflater.inflate(R.layout.books_list_item, viewGroup, false);

        viewHolder = (ViewHolder) view.getTag();

        if(viewHolder == null) {
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        viewHolder.updateBook(book);
        return view;


    }
}

