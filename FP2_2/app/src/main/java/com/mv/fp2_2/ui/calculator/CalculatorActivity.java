package com.mv.fp2_2.ui.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mv.fp2_2.R;
import com.mv.fp2_2.data.model.Calculator;

public class CalculatorActivity extends AppCompatActivity {

    private CalculatorMvpPresenter mPresenter;

    private TextView tvResult;

    private Button btnEnter;
    private Button btnClear;
    private Button btnUndo;

    public TextView getTvResult() {
        return tvResult;
    }

    private void init() {
        mPresenter = new CalculatorPresenter(
                new CalculatorView(this),
                new Calculator()
        );


        tvResult = findViewById(R.id.CalculatorAct_Tv_Result);

        btnEnter = findViewById(R.id.CalculatorAct_Btn_Enter);
        btnClear = findViewById(R.id.CalculatorAct_Btn_Clear);
        btnUndo = findViewById(R.id.CalculatorAct_Btn_Undo);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        init();


        btnEnter.setOnClickListener(view -> {
                mPresenter.onCalculate();
        });

        btnUndo.setOnClickListener(view -> {
            mPresenter.onUndoCalculation();
        });

        btnClear.setOnClickListener(view -> {
            mPresenter.onClearCalculation();
        });
    }



    public void OperationsBtn_OnClick(View view) {

        // Já que Button é uma subclasse da TextView converte-se a view para TextView
        // para obter o texto seja Button ou TextView
        String text = ((TextView)view).getText().toString();
        mPresenter.onAddToCalculation(text);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.terminate();
    }
}