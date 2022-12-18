package com.mv.fp7.ui.adapters;

import com.mv.fp7.data.model.Book;

public interface RecyclerViewAdapter {
    public void removeBook(Book bookRemoved);
    public void notifyBookChanged(Book updatedBook);
    public void notifyBookInserted(Book addedBook);
}
