package com.mv.fp6.ui.booksList;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mv.fp6.R;
import com.mv.fp6.data.model.Book;
import com.mv.fp6.data.model.SingletonBookManager;
import com.mv.fp6.ui.adapters.BooksListAdapter;
import com.mv.fp6.ui.adapters.RecyclerViewBooksAdapter;

public class BooksListFragment extends Fragment {

    private ListView listView;

    private RecyclerView recyclerView;

    public BooksListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books_list, container, false);




        // Para o customAdapter
        /*
        BooksListAdapter adapter = new BooksListAdapter(getContext(), SingletonBookManager.getInstance().getBookList());
        listView = view.findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Book selectedBook = (Book)adapterView.getItemAtPosition(i);

                Intent bookDetailsActivity = new Intent(getContext(), DetalhesLivroActivity.class);
                bookDetailsActivity.putExtra(DetalhesLivroActivity.BOOK_POSITION,i);
                startActivity(bookDetailsActivity);

            }
        });*/

        // Para o recyclerView
        recyclerView = view.findViewById(R.id.BooksListFrag_RecyV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        RecyclerViewBooksAdapter adapter = new RecyclerViewBooksAdapter(getContext(), SingletonBookManager.getInstance().getBookList());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }



}