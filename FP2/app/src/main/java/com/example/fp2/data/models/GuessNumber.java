package com.example.fp2.data.models;

import android.util.Log;

import java.util.Random;

public class GuessNumber {

    private int generatedNumber;
    private int maxNumber;
    private int minNumber;

    private final static String GUESS_HIGHER = "Experimente um número mais alto!";
    private final static String GUESS_LOWER = "Experimente um número mais baixo!";
    private final static String GUESS_SUCCESS = "Acertou! O número era ";

    public GuessNumber(int maxNumber, int minNumber) {
        this.maxNumber = maxNumber;
        this.minNumber = minNumber;

        generateRandomNumber();
    }

    public int getMaxNumber() {
        return maxNumber;
    }

    public int getMinNumber() {
        return minNumber;
    }

    // Método para gerar o random número
    private void generateRandomNumber() {
        Random r = new Random();

        // https://www.techiedelight.com/generate-random-integers-specified-range-java/
        generatedNumber = r.nextInt(maxNumber - minNumber + 1) + minNumber;
        Log.d("GeneratedNumber", "O número gerado foi " + generatedNumber);
    }


    // Método para calcular o resultado do guess
    public String guessNumber(int guess) {
        if (guess > generatedNumber)
            return GUESS_LOWER;
        else if (guess < generatedNumber)
            return GUESS_HIGHER;
        else
            return GUESS_SUCCESS + generatedNumber + ".";
    }
}
