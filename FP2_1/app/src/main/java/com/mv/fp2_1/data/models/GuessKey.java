package com.mv.fp2_1.data.models;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class GuessKey {

    private static final Random generator = new Random();

    private final int numNumbers;
    private final int numStars;
    private final int maxNum;
    private final int minNum;

    private int[] numbers;
    private int[] stars;

    public GuessKey(int numNumbers, int numStars, int maxNum, int minNum) {
        this.numNumbers = numNumbers;
        this.numStars = numStars;
        this.maxNum = maxNum;
        this.minNum = minNum;

        numbers = new int[this.numNumbers];
        stars = new int[this.numStars];
        generateKey();
    }

    public String getKey() {
        String fullKey = "";

        for (int i = 0; i < numbers.length; i++) {
            fullKey += numbers[i];
            
            if((i + 1) == numbers.length)
                fullKey += "\n";
            else
                fullKey += " ";
        }

        for (int star : stars) {
            fullKey += star + "";
        }
        
        fullKey.trim();
        return  fullKey;
    }

    public void generateKey() {
        String fullKey = "";
        for (int i = 0; i < numNumbers; i++) {
            numbers[i] = generator.nextInt(maxNum - minNum + 1) + minNum ;
            fullKey += numbers[i] + " ";
        }

        fullKey += "/ ";

        for (int i = 0; i < numStars; i++) {
            stars[i] = generator.nextInt(maxNum - minNum + 1) + minNum ;
            fullKey += stars[i] + " ";
        }

        Log.d("GeneratedKey", fullKey);
    }

    public String guess(int[] guessNumbers, int[] guessStars) {
        String correctGuess = "Acertou ";

        int counterCorrectNumber = 0;
        for (int guessNumber : guessNumbers) {
            for (int number : numbers) {
                if (guessNumber == number)
                    counterCorrectNumber++;
            }
        }

        correctGuess += counterCorrectNumber + " números e ";

        int counterCorrectStar = 0;
        for (int guessStar : guessStars) {
            for (int star : stars) {
                if (guessStar == star)
                    counterCorrectStar++;
            }
        }

        correctGuess += counterCorrectStar + " chaves!";

        return  correctGuess;

    }
}
