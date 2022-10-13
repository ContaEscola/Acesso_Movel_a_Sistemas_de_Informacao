package com.mv.fp2_2.ui.calculator;

import android.util.Log;
import android.widget.Toast;

import com.mv.fp2_2.data.model.Calculator;

public class CalculatorPresenter implements CalculatorMvpPresenter{

    private CalculatorMvpView mView;
    private Calculator mModel;

    public CalculatorPresenter(CalculatorMvpView mView, Calculator mModel) {
        this.mView = mView;
        this.mModel = mModel;
    }

    @Override
    public void onCalculate()  {
        try {
            String result = mModel.calculate();
            mView.setResult(result);

        } catch (NumberFormatException numberExcept) {
            mView.showException("A expressão não é válida!");
        }

    }

    @Override
    public void onAddToCalculation(String toAdd) {
        String finalCalculation = mModel.append(toAdd);
        mView.setResult(finalCalculation);
    }

    @Override
    public void onUndoCalculation() {
        String finalCalculation = mModel.undo();
        mView.setResult(finalCalculation);
    }

    @Override
    public void onClearCalculation() {
        mModel.clear();
        mView.setResult("");
    }

    @Override
    public void terminate() {
        mView.terminate();
    }
}
