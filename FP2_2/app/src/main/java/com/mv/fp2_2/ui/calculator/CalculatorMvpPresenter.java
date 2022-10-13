package com.mv.fp2_2.ui.calculator;

import com.mv.fp2_2.ui.base.MvpPresenter;

public interface CalculatorMvpPresenter extends MvpPresenter {

    void onCalculate();

    void onAddToCalculation(String toAdd);

    void onClearCalculation();

    void onUndoCalculation();
}
