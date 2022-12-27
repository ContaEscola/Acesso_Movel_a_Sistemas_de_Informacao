package com.mv.fp8.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.mv.fp8.R;
import com.mv.fp8.data.db.model.Book;

import java.util.ArrayList;

public class GrelhaLivroAdapter extends BaseAdapter {

    private class ViewHolderGrelha {
        private ImageView cover;
        private Book book;

        public ViewHolderGrelha(View view, Book book) {
            this(view);
            this.book = book;
        }

        public ViewHolderGrelha (View view) {
            cover = view.findViewById(R.id.BooksGridItem_Iv_CoverPlaceholder);
        }

        public void updateBook() {
            cover.setImageResource(book.getCover());
        }

    }

    private Context context;
    private LayoutInflater layoutInflater;
    private ArrayList<Book> booksList;


    public GrelhaLivroAdapter(Context context, ArrayList<Book> booksList){
        this.context = context;
        this.booksList = booksList;
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

        ViewHolderGrelha viewHolder;
        if(view == null) {
            view = layoutInflater.inflate(R.layout.item_grelha_livro, null);

            viewHolder = new ViewHolderGrelha(view, book);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolderGrelha) view.getTag();
        }

        viewHolder.updateBook();
        return view;
    }
}
