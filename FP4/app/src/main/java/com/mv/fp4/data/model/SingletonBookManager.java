package com.mv.fp4.data.model;

import com.mv.fp4.R;

import java.util.ArrayList;

public class SingletonBookManager {

    private static SingletonBookManager instance = null;
    private ArrayList<Book> bookList;

    private SingletonBookManager() {
        this.bookList = new ArrayList<>();
        generateDynamicData();
    }

    public static synchronized SingletonBookManager getInstance(){
        if (instance == null)
            instance = new SingletonBookManager();

        return instance;
    }

    private void generateDynamicData() {
        Book dynamicBook = new Book(1, "Programar em Android AMSI", "2º Temporada", "AMSI Team", 2019, R.drawable.img_book_cover_2);
        bookList.add(dynamicBook);
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }
}
