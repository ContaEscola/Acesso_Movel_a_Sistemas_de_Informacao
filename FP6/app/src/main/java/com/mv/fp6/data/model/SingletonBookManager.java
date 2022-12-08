package com.mv.fp6.data.model;

import com.mv.fp6.R;

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
        bookList.add(new Book(1,2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 1","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2,2021, R.drawable.img_book_cover_2, "Programar em Android AMSI - 2","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(3,2021, R.drawable.ic_politecnico_leiria, "Programar em Android AMSI - 3","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(4,2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 4","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(5,2021, R.drawable.img_book_cover_2, "Programar em Android AMSI - 5","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(6,2021, R.drawable.ic_politecnico_leiria, "Programar em Android AMSI - 6","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(7,2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 7","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(8,2021, R.drawable.img_book_cover_2, "Programar em Android AMSI - 8","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(9,2021, R.drawable.ic_politecnico_leiria, "Programar em Android AMSI - 9","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(10,2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 10","2ª Temporada", "AMSI TEAM"));
    }


    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public Book getBook(int index) {
        return bookList.get(index);
    }
}
