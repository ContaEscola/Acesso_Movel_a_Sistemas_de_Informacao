package com.mv.fp8.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mv.fp8.data.db.model.Book;
import com.mv.fp8.data.db.model.SingletonBookManager;

import java.util.LinkedList;

public class AppDBManager {

    private static AppDBManager instance = null;
    private static DBOpenHelper dbOpenHelper = null;
    private Context context;
    private final SQLiteDatabase database;

    private AppDBManager(Context context) {
        this.context = context;
        if(this.dbOpenHelper == null)
            this.dbOpenHelper = new DBOpenHelper(context);
        this.database = dbOpenHelper.getWritableDatabase();
    }

    public static synchronized AppDBManager getInstance(Context context){
        if (instance == null)
            instance = new AppDBManager(context);

        return instance;
    }

    private ContentValues convertBookToContentValues(Book book) {
        ContentValues values = new ContentValues();

        values.put(DBOpenHelper.COL_ID_BOOK, book.getId());
        values.put(DBOpenHelper.COL_TITLE_BOOK, book.getTitle());
        values.put(DBOpenHelper.COL_SERIE_BOOK, book.getSerie());
        values.put(DBOpenHelper.COL_AUTHOR_BOOK, book.getAuthor());
        values.put(DBOpenHelper.COL_YEAR_BOOK, book.getYear());
        values.put(DBOpenHelper.COL_COVER_BOOK, book.getCover());

        return values;
    }

    public LinkedList<Book> getAllBooksDB() {
        LinkedList<Book> books = new LinkedList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + DBOpenHelper.TBL_BOOK, null);

        if(cursor.moveToFirst()){

            do {
                books.add(new Book(cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(4),
                        cursor.getInt(5)));
            } while (cursor.moveToNext());
        }

        return books;
    }

    public int getLastBookIdDB() {
        int lastId = 0;
        Cursor cursor = database.rawQuery("SELECT MAX(?) FROM " + DBOpenHelper.TBL_BOOK, new String[]{DBOpenHelper.COL_ID_BOOK});
        if(cursor.moveToFirst())
            lastId = cursor.getInt(0);

        return lastId;
    }

    public void addBookDB(Book book) {
        ContentValues values = convertBookToContentValues(book);
        database.insert(DBOpenHelper.TBL_BOOK, null, values);
    }

    public void addBookListDB(LinkedList<Book> bookList) {
        for (Book book : bookList) {
            addBookDB(book);
        }
    }

    public boolean editBookDB(Book book) {
        ContentValues values = convertBookToContentValues(book);

        return database.update(DBOpenHelper.TBL_BOOK, values, "id = ?", new String[]{String.valueOf(book.getId())}) > 0;
    }

    public void removeBookDB(int bookId) {
        database.delete(DBOpenHelper.TBL_BOOK, "id = ?", new String[]{String.valueOf(bookId)});
    }
}
