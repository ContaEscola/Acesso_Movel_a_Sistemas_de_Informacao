package com.mv.fp2_1.ui.guessKey;

import com.mv.fp2_1.data.models.GuessKey;

public class GuessKeyPresenter implements GuessKeyMvpPresenter {

    private final GuessKeyMvpView mView;
    private final GuessKey mModel;

    public GuessKeyPresenter(GuessKeyMvpView mView, GuessKey mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void onGuess(int[] numbers, int[] stars) {



    }

    @Override
    public void terminate() {
        mView.terminate();
    }
}
