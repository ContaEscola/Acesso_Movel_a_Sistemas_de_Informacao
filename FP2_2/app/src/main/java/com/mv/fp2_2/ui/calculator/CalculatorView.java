package com.mv.fp2_2.ui.calculator;

import android.widget.Toast;

public class CalculatorView implements CalculatorMvpView {

    private CalculatorActivity mActivity;

    public CalculatorView(CalculatorActivity mActivity) {
        this.mActivity = mActivity;
    }




    @Override
    public void setResult(String result) {
        if(mActivity == null) return;

        mActivity.getTvResult().setText(result);
    }

    @Override
    public void showException(String message) {
        Toast.makeText(mActivity.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void terminate() {
        mActivity = null;
    }
}
