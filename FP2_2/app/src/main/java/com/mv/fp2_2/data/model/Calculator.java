package com.mv.fp2_2.data.model;

import com.itis.libs.parserng.android.expressParser.MathExpression;

import java.text.DecimalFormat;


public class Calculator {

    private String calculation;


    public Calculator() {
        this.calculation = "";
    }

    public String calculate() throws NumberFormatException {

        MathExpression expression = new MathExpression(calculation);
        double result = Double.valueOf(expression.solve());

        DecimalFormat formatter = new DecimalFormat("0.########");
        calculation = formatter.format(result);

        return calculation;
    }

    public String append(String toAppend) {
        return calculation += toAppend;

    }

    public String undo() {
        if (calculation.isEmpty()) return calculation;

        calculation =  calculation.substring(0,  calculation.length() - 1);
        return calculation;
    }

    public void clear() {
        calculation = "";
    }
}
