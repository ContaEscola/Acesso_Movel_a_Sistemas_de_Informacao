package com.mv.fp2_1.ui.guessKey;

import com.mv.fp2_1.ui.base.MvpPresenter;

public interface GuessKeyMvpPresenter extends MvpPresenter {

    void onGuess(int[] numbers, int[] stars);

}
