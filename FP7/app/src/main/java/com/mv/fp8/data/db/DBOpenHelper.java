package com.mv.fp8.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mv.fp8.data.db.model.Book;

import java.util.LinkedList;

public class DBOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "fp8";
    private static final int DB_VERSION = 1;

    public static final String TBL_BOOK = "book";
    public static final String COL_ID_BOOK = "id";
    public static final String COL_TITLE_BOOK = "title";
    public static final String COL_SERIE_BOOK = "serie";
    public static final String COL_AUTHOR_BOOK = "author";
    public static final String COL_YEAR_BOOK = "year";
    public static final String COL_COVER_BOOK = "cover";

    private static final String CREATE_TBL_BOOK =
            "CREATE TABLE " + TBL_BOOK + "( " +
                    COL_ID_BOOK + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_TITLE_BOOK + " TEXT NOT NULL, " +
                    COL_SERIE_BOOK + " TEXT NOT NULL, " +
                    COL_AUTHOR_BOOK + " TEXT NOT NULL, " +
                    COL_YEAR_BOOK + " INTEGER NOT NULL, " +
                    COL_COVER_BOOK + " INTEGER " +
                    ");";


    private final SQLiteDatabase database;

    public DBOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.database = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TBL_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TBL_BOOK);
        this.onCreate(database);
    }


}
