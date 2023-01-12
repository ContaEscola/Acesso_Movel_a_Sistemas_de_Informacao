package com.mv.fp8.ui.adapters;

import com.mv.fp8.data.db.model.Book;

public interface RecyclerViewAdapter {
    public void removeBook(Book bookRemoved);
    public void notifyBookChanged(Book updatedBook);
    public void notifyBookInserted(Book addedBook);
}
