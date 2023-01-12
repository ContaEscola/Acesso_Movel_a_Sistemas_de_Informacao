package com.mv.fp8.data.db.model;

import android.content.Context;

import com.mv.fp8.R;
import com.mv.fp8.data.db.AppDBManager;
import com.mv.fp8.data.db.DBOpenHelper;
import com.mv.fp8.ui.adapters.RecyclerViewAdapter;

import java.util.LinkedList;

public class SingletonBookManager {

    private static SingletonBookManager instance = null;
    private LinkedList<Book> bookList;

    private Context context;
    private RecyclerViewAdapter adapter;


    private SingletonBookManager(Context context) {
        this.bookList = new LinkedList<>();
        this.context = context;

        this.bookList = AppDBManager.getInstance(context).getAllBooksDB();
        if(bookList.size() == 0) {
            generateDynamicData();
            AppDBManager.getInstance(context).addBookListDB(this.bookList);
        }

    }

    public static synchronized SingletonBookManager getInstance(Context context){
        if (instance == null)
            instance = new SingletonBookManager(context);

        return instance;
    }


    public void bindAdapter(RecyclerViewAdapter adapter) {
        this.adapter = (adapter);
    }
    
    private void generateDynamicData() {
        bookList.add(new Book("Programar em Android AMSI - 1","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_1));
        bookList.add(new Book("Programar em Android AMSI - 2","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_2));
        bookList.add(new Book("Programar em Android AMSI - 3","2ª Temporada", "AMSI TEAM", 2021, R.drawable.ic_politecnico_leiria));

        bookList.add(new Book("Programar em Android AMSI - 4","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_1));
        bookList.add(new Book("Programar em Android AMSI - 5","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_2));
        bookList.add(new Book("Programar em Android AMSI - 6","2ª Temporada", "AMSI TEAM", 2021, R.drawable.ic_politecnico_leiria));

        bookList.add(new Book("Programar em Android AMSI - 7","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_1));
        bookList.add(new Book("Programar em Android AMSI - 8","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_2));
        bookList.add(new Book("Programar em Android AMSI - 9","2ª Temporada", "AMSI TEAM", 2021, R.drawable.ic_politecnico_leiria));

        bookList.add(new Book("Programar em Android AMSI - 10","2ª Temporada", "AMSI TEAM", 2021, R.drawable.img_book_cover_1));
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

    private void notifyRemoveOnAdapter(Book bookRemoved){
        adapter.removeBook(bookRemoved);
    }
    private void notifyUpdateOnAdapter(Book bookUpdated) { adapter.notifyBookChanged(bookUpdated); }
    private void notifyAddOnAdapter(Book bookAdded) { adapter.notifyBookInserted(bookAdded); }

}
