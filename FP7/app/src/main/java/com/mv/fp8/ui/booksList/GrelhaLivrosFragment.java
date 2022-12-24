package com.mv.fp8.ui.booksList;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mv.fp8.R;
import com.mv.fp8.data.model.SingletonBookManager;

import com.mv.fp8.ui.MenuMainActivity;
import com.mv.fp8.ui.adapters.RecyclerViewGridBooksAdapter;


public class GrelhaLivrosFragment extends Fragment {

    private GridView gridView;

    private RecyclerView recyclerView;
    private RecyclerViewGridBooksAdapter adapter;

    private SwipeRefreshLayout swipeContainer;

    public GrelhaLivrosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grelha_livros, container, false);

        swipeContainer = view.findViewById(R.id.BooksGridFrag_SwipeContainer);
        /*
        GrelhaLivroAdapter booksGridAdapter = new GrelhaLivroAdapter(getContext(), SingletonBookManager.getInstance().getBookList());

        gridView = view.findViewById(R.id.BooksGridFrag_Gv);
        gridView.setAdapter(booksGridAdapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Book book = (Book) booksGridAdapter.getItem(i);
                Intent bookDetailsActivity = new Intent(getContext(), DetalhesLivroActivity.class);
                bookDetailsActivity.putExtra(DetalhesLivroActivity.BOOK_ID,book.getId());
                startActivity(bookDetailsActivity);
            }
        });

        */

        recyclerView = view.findViewById(R.id.BooksGridFrag_RecyV);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new RecyclerViewGridBooksAdapter(getContext(), SingletonBookManager.getInstance().getBookList());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton floatAdd = view.findViewById(R.id.BooksGridFrag_FloatBtn_Add);
        floatAdd.setOnClickListener(fragmentView -> {
            addBook();
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter = new RecyclerViewGridBooksAdapter(getContext(), SingletonBookManager.getInstance().getBookList());
                recyclerView.setAdapter(adapter);
                swipeContainer.setRefreshing(false);


            }
        });


        return view;

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == MenuMainActivity.REQUEST_DETAIL_ACTIVITY) {
            if(resultCode == RESULT_OK) {
                String resultMessage = data.getStringExtra(DetalhesLivroActivity.RESULT_MESSAGE);
                Snackbar.make(getView(), resultMessage, Snackbar.LENGTH_SHORT).show();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }


    private void addBook() {
        Intent bookDetailsActivity = new Intent(getContext(), DetalhesLivroActivity.class);
        bookDetailsActivity.putExtra(DetalhesLivroActivity.SCENARIO_KEY, DetalhesLivroActivity.SCENARIO_ADD);
        startActivityForResult(bookDetailsActivity, MenuMainActivity.REQUEST_DETAIL_ACTIVITY);
    }
}