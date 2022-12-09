package com.mv.fp6.ui.booksList;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.mv.fp6.R;
import com.mv.fp6.data.model.SingletonBookManager;
import com.mv.fp6.ui.adapters.GrelhaLivroAdapter;


public class GrelhaLivrosFragment extends Fragment {

    private GridView gridView;

    public GrelhaLivrosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grelha_livros, container, false);

        GrelhaLivroAdapter booksGridAdapter = new GrelhaLivroAdapter(getContext(), SingletonBookManager.getInstance().getBookList());

        gridView = view.findViewById(R.id.BooksGridFrag_Gv);
        gridView.setAdapter(booksGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent bookDetailsActivity = new Intent(getContext(), DetalhesLivroActivity.class);
                bookDetailsActivity.putExtra(DetalhesLivroActivity.BOOK_POSITION,i);
                startActivity(bookDetailsActivity);
            }
        });

        return view;

    }
}