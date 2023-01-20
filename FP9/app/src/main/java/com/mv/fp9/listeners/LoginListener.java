package com.mv.fp9.listeners;

import android.content.Context;

public interface LoginListener {

    void onValidLogin(final String token, final String email, final Context context);
    void onInvalidLogin(final String message,final Context context);
}
