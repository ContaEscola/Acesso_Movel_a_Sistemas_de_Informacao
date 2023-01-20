package com.mv.fp9.data.db.model;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.android.material.snackbar.Snackbar;
import com.mv.fp9.R;
import com.mv.fp9.data.db.AppDBManager;
import com.mv.fp9.data.network.ApiBodyHelper;
import com.mv.fp9.data.network.ApiEndPoints;
import com.mv.fp9.listeners.BooksListener;
import com.mv.fp9.ui.adapters.RecyclerViewAdapter;
import com.mv.fp9.utils.NetworkUtils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;

public class SingletonBookManager {

    private static SingletonBookManager instance = null;
    private LinkedList<Book> bookList;

    private Context context;
    private RecyclerViewAdapter adapter;

    private static RequestQueue volleyQueue;

    private BooksListener booksListener;

    public void setBooksListener(BooksListener listener) {
        booksListener = listener;
    }

    private SingletonBookManager(Context context) {
        this.bookList = new LinkedList<>();
        this.context = context;

        volleyQueue = Volley.newRequestQueue(context.getApplicationContext());
        getBooksApi();

    }

    public static synchronized SingletonBookManager getInstance(Context context){
        if (instance == null) {
            instance = new SingletonBookManager(context);
        }

        return instance;
    }


    public void bindAdapter(RecyclerViewAdapter adapter) {
        this.adapter = (adapter);
    }



    public LinkedList<Book> getBookListDB() {
        return bookList;
    }

    public Book getBook(int id) {
        for(Book book : bookList) {
            if(book.getId() == id)
                return book;
        }
        return null;
    }

    public int getBookIndex(int id) {
        int indexIncrementer = 0;
        for (Book book: bookList){
            if(book.getId() == id)
                return indexIncrementer;

            indexIncrementer++;
        }

        return -1;
    }

    public void addBooksDB(Book[] books) {
        LinkedList<Book> linkedList = new LinkedList<>();
        Collections.addAll(linkedList, books);
        AppDBManager.getInstance(context).addBookListDB(linkedList);
    }

    public void addBookDB(Book book) {
        bookList.add(book);
        AppDBManager.getInstance(context).addBookDB(book);
        notifyAddOnAdapter(book);
    }

    public void removeBookDB(int bookId) {
        Book book = getBook(bookId);
        bookList.remove(book);
        AppDBManager.getInstance(context).removeBookDB(bookId);
        notifyRemoveOnAdapter(book);
    }

    public void editBookDB(Book updatedBook) {
        int updateIndex = getBookIndex(updatedBook.getId());
        bookList.set(updateIndex, updatedBook);
        AppDBManager.getInstance(context).editBookDB(updatedBook);
        notifyUpdateOnAdapter(updatedBook);
    }

    private void notifyRemoveOnAdapter(Book bookRemoved){ adapter.removeBook(bookRemoved); }
    private void notifyUpdateOnAdapter(Book bookUpdated) { adapter.notifyBookChanged(bookUpdated); }
    private void notifyAddOnAdapter(Book bookAdded) { adapter.notifyBookInserted(bookAdded); }


    private void getBooksApi() {
        if(!NetworkUtils.hasInternetConnection(context)) return;
        JsonArrayRequest jsonBooksReq = new JsonArrayRequest(Request.Method.GET, ApiEndPoints.ENDPOINT_BOOKS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    Book[] books = Book.parseJsonToBooks(response.toString());
                    bookList.clear();
                    Collections.addAll(bookList, books);
                    AppDBManager.getInstance(context).removeAllBooksDB();
                    addBooksDB(books);
                    booksListener.onRefreshBooksList(bookList);
                } catch (JsonProcessingException e) {
                    Log.d("ApiError", "Get Books Error:" + e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("ApiError", "Get Books Error:" + error.getMessage());
            }
        });
        volleyQueue.add(jsonBooksReq);
    }
    public void addBookApi(final Book book, final String token) {
        if(!NetworkUtils.hasInternetConnection(context)) return;
        try {
            String jsonBook = Book.convertBookToJson(book);
            String jsonWithToken = ApiBodyHelper.addTokenToJsonBody(jsonBook, token);
            Map<String, Object> jsonParams = ApiBodyHelper.convertJsonStringToMap(jsonWithToken);
            JsonObjectRequest jsonBookReq = new JsonObjectRequest(Request.Method.POST, ApiEndPoints.ENDPOINT_BOOKS, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(response.has("message") || response.has("errors") || response.has("error")) {
                        Log.d("ApiError", "Add Book Error:" + response.toString());
                        return;
                    }
                    addBookDB(book);
                    booksListener.onRefreshBooksList(bookList);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ApiError", "Add Book Error:" + error.getMessage());
                }
            });

            volleyQueue.add(jsonBookReq);
        } catch (JsonProcessingException e) {
            Log.d("ApiError", e.getMessage());
        }
    }
    public void removeBookApi(final Book book) {
        if(!NetworkUtils.hasInternetConnection(context)) return;
        String bookUrl = ApiEndPoints.ENDPOINT_BOOKS + "/" + book.getId();
        StringRequest stringBookReq = new StringRequest(Request.Method.DELETE, bookUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.contains("\"message\"")) {
                    Log.d("ApiError", "Remove Book Error:" + response.toString());
                    return;
                }
                removeBookDB(book.getId());
                booksListener.onRefreshBooksList(bookList);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ApiError", "Remove Book Error:" + error.getMessage());
            }
        });

        volleyQueue.add(stringBookReq);
    }
    public void editBookApi(final Book book, final String token) {
        if(!NetworkUtils.hasInternetConnection(context)) return;
        try {
            String jsonBook = Book.convertBookToJson(book);
            String jsonWithToken = ApiBodyHelper.addTokenToJsonBody(jsonBook, token);
            Map<String, Object> jsonParams = ApiBodyHelper.convertJsonStringToMap(jsonWithToken);
            String bookUrl = ApiEndPoints.ENDPOINT_BOOKS + "/" + book.getId();
            JsonObjectRequest jsonBookReq = new JsonObjectRequest(Request.Method.PUT, bookUrl, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    if(response.has("message") || response.has("errors") || response.has("error")) {
                        Log.d("ApiError", "Edit Book Error:" + response.toString());
                        return;
                    }
                    editBookDB(book);
                    booksListener.onRefreshBooksList(bookList);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.d("ApiError", "Edit Book Error:" + error.getMessage());
                }
            });

            volleyQueue.add(jsonBookReq);
        } catch (JsonProcessingException e) {
            Log.d("ApiError", e.getMessage());
        }

    }

}
