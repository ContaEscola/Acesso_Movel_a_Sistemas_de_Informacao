package com.example.fp2.ui.guessNumber;

import com.example.fp2.ui.base.MvpPresenter;

public interface GuessNumberMvpPresenter extends MvpPresenter {

    // Método para fazer o guess do número
    void onGuessing(int guess);
}
