package com.mv.fp5.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mv.fp5.R;
import com.mv.fp5.data.model.Book;
import com.mv.fp5.data.model.SingletonBookManager;

import java.util.ArrayList;

public class BooksListAdapter extends BaseAdapter {

    private static class ViewHolder {
        TextView title;
        TextView serie;
        TextView author;
        TextView year;
        ImageView cover;

    }

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Book> booksList;

    public BooksListAdapter(Context context, ArrayList<Book> booksList) {
        this.context = context;
        this.booksList = booksList;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Book getItem(int i) {
        return SingletonBookManager.getInstance().getBook(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Book book = getItem(i);

        ViewHolder viewHolder;
        if(view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.books_list_item, parent, false);

            viewHolder.title = view.findViewById(R.id.BooksListItem_Tv_TitlePlaceholder);
            viewHolder.serie = view.findViewById(R.id.BooksListItem_Tv_SeriePlaceholder);
            viewHolder.author = view.findViewById(R.id.BooksListItem_Tv_AutorPlaceholder);
            viewHolder.year = view.findViewById(R.id.BooksListItem_Tv_AnoPlaceholder);
            viewHolder.cover = view.findViewById(R.id.BooksListItem_Iv_BookCoverPlaceholder);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.title.setText(book.getTitle());
        viewHolder.serie.setText(book.getSerie());
        viewHolder.author.setText(book.getAuthor());
        viewHolder.year.setText(book.getYear());
        viewHolder.cover.setImageResource();
        return view;


    }
}

