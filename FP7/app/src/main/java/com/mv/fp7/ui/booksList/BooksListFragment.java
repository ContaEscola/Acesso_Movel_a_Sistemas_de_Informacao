package com.mv.fp7.ui.booksList;

import static android.app.Activity.RESULT_OK;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.mv.fp7.R;
import com.mv.fp7.data.model.SingletonBookManager;
import com.mv.fp7.ui.MenuMainActivity;
import com.mv.fp7.ui.adapters.RecyclerViewBooksAdapter;

public class BooksListFragment extends Fragment {


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

        swipeContainer = view.findViewById(R.id.BooksListFrag_SwipeContainer);

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

        adapter = new RecyclerViewBooksAdapter(getContext(), SingletonBookManager.getInstance().getBookList());
        recyclerView.setAdapter(adapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        FloatingActionButton floatAdd = view.findViewById(R.id.BooksListFrag_FloatBtn_Add);
        floatAdd.setOnClickListener(fragmentView -> {
            addBook();
        });

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                adapter = new RecyclerViewBooksAdapter(getContext(), SingletonBookManager.getInstance().getBookList());
                recyclerView.setAdapter(adapter);
                swipeContainer.setRefreshing(false);


            }
        });

        return view;
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

}