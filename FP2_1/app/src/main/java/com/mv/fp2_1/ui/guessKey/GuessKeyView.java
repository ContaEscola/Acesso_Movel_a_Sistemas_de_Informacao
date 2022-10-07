package com.mv.fp2_1.ui.guessKey;

public class GuessKeyView implements GuessKeyMvpView {

    private GuessKeyActivity mActivity;

    public GuessKeyView(GuessKeyActivity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void displayKey(String key) {
        if (mActivity == null) return;
        mActivity.getTvKey().setText(key);
    }

    @Override
    public void displayResult(String result) {
        if (mActivity == null) return;
        mActivity.getTvResult().setText(result);
    }

    @Override
    public void terminate() {
        mActivity = null;
    }
}
