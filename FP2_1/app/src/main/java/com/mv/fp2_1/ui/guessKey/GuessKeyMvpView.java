package com.mv.fp2_1.ui.guessKey;

import com.mv.fp2_1.ui.base.MvpView;

public interface GuessKeyMvpView extends MvpView {

    void displayKey(String key);
    void displayResult(String result);
}
