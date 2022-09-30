package com.example.fp2.ui.guessNumber;

import com.example.fp2.data.models.GuessNumber;

public class GuessNumberPresenter implements GuessNumberMvpPresenter {

    private GuessNumberMvpView mView;
    private GuessNumber mModel;

    public GuessNumberPresenter(GuessNumberMvpView view, GuessNumber model) {
        this.mView = view;
        this.mModel = model;
    }

    @Override
    public void onGuessing(int guess) {
        // Recebe o resultado do guess com o método do Model
        String result = mModel.guessNumber(guess);

        // Envia o resultado para a view
        mView.setResult(result);
    }



    @Override
    public void terminate() {
        mView.terminate();
    }
}
