package com.mv.fp7.data.model;

import com.mv.fp7.R;
import com.mv.fp7.ui.adapters.RecyclerViewBooksAdapter;

import java.util.ArrayList;

public class SingletonBookManager {

    private static SingletonBookManager instance = null;
    private ArrayList<Book> bookList;

    private ArrayList<RecyclerViewBooksAdapter> adapters;


    private SingletonBookManager() {
        this.bookList = new ArrayList<>();
        this.adapters = new ArrayList<>();
        generateDynamicData();
    }

    public static synchronized SingletonBookManager getInstance(){
        if (instance == null)
            instance = new SingletonBookManager();

        return instance;
    }

    public void bindAdapter(RecyclerViewBooksAdapter adapter) {
        adapters.add(adapter);
    }

    private void generateDynamicData() {
        bookList.add(new Book(2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 1","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.img_book_cover_2, "Programar em Android AMSI - 2","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.ic_politecnico_leiria, "Programar em Android AMSI - 3","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 4","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.img_book_cover_2, "Programar em Android AMSI - 5","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.ic_politecnico_leiria, "Programar em Android AMSI - 6","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 7","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.img_book_cover_2, "Programar em Android AMSI - 8","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.ic_politecnico_leiria, "Programar em Android AMSI - 9","2ª Temporada", "AMSI TEAM"));
        bookList.add(new Book(2021, R.drawable.img_book_cover_1, "Programar em Android AMSI - 10","2ª Temporada", "AMSI TEAM"));
    }


    public ArrayList<Book> getBookList() {
        return bookList;
    }

    public Book getBook(int id) {
        for(Book book : bookList) {
            if(book.getId() == id)
                return book;
        }
        return null;
    }

    public void addBook(Book book) {
        bookList.add(book);
    }

    public void removeBook(int bookId) {
        Book book = getBook(bookId);
        bookList.remove(book);
        notifyDataChangesForAdapters();
    }

    public void editBook(Book updatedBook) {
        Book oldBook = getBook(updatedBook.getId());
        oldBook = updatedBook;
    }

    private void notifyDataChangesForAdapters(){
        for (RecyclerViewBooksAdapter adapter: adapters)
            adapter.notifyDataSetChanged();
    }
}
