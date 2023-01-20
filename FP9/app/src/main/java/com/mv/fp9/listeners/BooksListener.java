package com.mv.fp9.listeners;

import com.mv.fp9.data.db.model.Book;

import java.util.LinkedList;

public interface BooksListener {
    void onRefreshBooksList(LinkedList<Book> booksList);
}
