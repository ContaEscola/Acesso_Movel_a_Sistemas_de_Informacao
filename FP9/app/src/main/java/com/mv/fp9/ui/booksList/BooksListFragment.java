package com.mv.fp9.ui.booksList;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mv.fp9.R;
import com.mv.fp9.data.db.model.Book;
import com.mv.fp9.data.db.model.SingletonBookManager;
import com.mv.fp9.listeners.BooksListener;
import com.mv.fp9.ui.MenuMainActivity;
import com.mv.fp9.ui.adapters.RecyclerViewBooksAdapter;

import java.util.LinkedList;

public class BooksListFragment extends Fragment implements BooksListener {


    private ListView listView;

    private RecyclerView recyclerView;
    private RecyclerViewBooksAdapter adapter;

    private SwipeRefreshLayout swipeContainer;

    public BooksListFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_books_list, container, false);
        setHasOptionsMenu(true);
        SingletonBookManager.getInstance(getContext()).setBooksListener(this);
        swipeContainer = view.findViewById(R.id.BooksListFrag_SwipeContainer);


        // Para o recyclerView
        recyclerView = view.findViewById(R.id.BooksListFrag_RecyV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RecyclerViewBooksAdapter(getContext(), SingletonBookManager.getInstance(getContext()).getBookListDB());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton floatAdd = view.findViewById(R.id.BooksListFrag_FloatBtn_Add);
        floatAdd.setOnClickListener(fragmentView -> {
            addBook();
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                refreshAdapter(SingletonBookManager.getInstance(getContext()).getBookListDB());
                swipeContainer.setRefreshing(false);


            }
        });

        return view;
    }


    private void refreshAdapter(LinkedList<Book> booksList) {
        adapter = new RecyclerViewBooksAdapter(getContext(), booksList);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa, menu);


        SearchView searchView = (SearchView) menu.findItem(R.id.MenuSearch_ItemSearch).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter(s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
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

    @Override
    public void onRefreshBooksList(LinkedList<Book> booksList) {
        refreshAdapter(booksList);
    }
}