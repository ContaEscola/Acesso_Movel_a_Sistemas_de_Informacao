package com.mv.fp2_1.ui.guessKey;

import com.mv.fp2_1.data.models.GuessKey;

import java.util.Map;

public class GuessKeyPresenter implements GuessKeyMvpPresenter {

    private final GuessKeyMvpView mView;
    private final GuessKey mModel;

    public GuessKeyPresenter(GuessKeyMvpView mView, GuessKey mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void onGuess(int[] numbers, int[] stars) {
        mModel.generateKey();
        String key = mModel.getKey();
        String result = mModel.guess(numbers, stars);

        mView.displayKey(key);
        mView.displayResult(result);

    }

    @Override
    public void terminate() {
        mView.terminate();
    }
}
