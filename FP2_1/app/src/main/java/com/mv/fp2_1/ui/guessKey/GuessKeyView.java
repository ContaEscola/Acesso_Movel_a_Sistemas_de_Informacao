package com.mv.fp2_1.ui.guessKey;

public class GuessKeyView implements GuessKeyMvpView {

    private GuessKeyActivity mActivity;

    public GuessKeyView(GuessKeyActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void displayKey(String key) {
        mActivity.getTvKey().setText(key);
    }

    @Override
    public void displayResult(String result) {
        mActivity.getTvResult().setText(result);
    }

    @Override
    public void terminate() {
        mActivity = null;
    }
}
