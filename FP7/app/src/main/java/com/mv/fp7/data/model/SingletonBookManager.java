package com.mv.fp7.data.model;

import com.mv.fp7.R;
import com.mv.fp7.ui.adapters.RecyclerViewAdapter;
import com.mv.fp7.ui.adapters.RecyclerViewBooksAdapter;

import java.util.ArrayList;

public class SingletonBookManager {

    private static SingletonBookManager instance = null;
    private ArrayList<Book> bookList;

    private RecyclerViewAdapter adapter;


    private SingletonBookManager() {
        this.bookList = new ArrayList<>();
        generateDynamicData();
    }

    public static synchronized SingletonBookManager getInstance(){
        if (instance == null)
            instance = new SingletonBookManager();

        return instance;
    }

    public void bindAdapter(RecyclerViewAdapter adapter) {
        this.adapter = (adapter);
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

    public int getBookIndex(int id) {
        int indexIncrementer = 0;
        for (Book book: bookList){
            if(book.getId() == id)
                return indexIncrementer;

            indexIncrementer++;
        }

        return -1;
    }

    public void addBook(Book book) {
        bookList.add(book);
        notifyAddOnAdapter(book);
    }

    public void removeBook(int bookId) {
        Book book = getBook(bookId);
        bookList.remove(book);
        notifyRemoveOnAdapter(book);
    }

    public void editBook(Book updatedBook) {
        int updateIndex = getBookIndex(updatedBook.getId());
        bookList.set(updateIndex, updatedBook);
        notifyUpdateOnAdapter(updatedBook);
    }

    private void notifyRemoveOnAdapter(Book bookRemoved){
        adapter.removeBook(bookRemoved);
    }
    private void notifyUpdateOnAdapter(Book bookUpdated) { adapter.notifyBookChanged(bookUpdated); }
    private void notifyAddOnAdapter(Book bookAdded) { adapter.notifyBookInserted(bookAdded); }

}
