package com.example.fp2.ui.guessNumber;

public class GuessNumberView implements  GuessNumberMvpView {

    private GuessNumberActivity mActivity;

    public GuessNumberView(GuessNumberActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void setResult(String result) {
        if(mActivity == null) return;


        // Atualiza o UI com o resultado obtido
        mActivity.getTvResult().setText(result);

        // Reseta o texto no input do texto
        mActivity.getEtGuessNumber().getText().clear();

    }

    @Override
    public void terminate() {
        mActivity = null;
    }
}
