package com.mv.fp2_2.ui.calculator;

import com.mv.fp2_2.ui.base.MvpView;

public interface CalculatorMvpView extends MvpView {


    void setResult(String result);
    void showException(String message);
}
