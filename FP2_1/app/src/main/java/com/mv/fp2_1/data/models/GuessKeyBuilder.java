package com.mv.fp2_1.data.models;

public class GuessKeyBuilder {

    private int numNumbers = 5;
    private int numStars = 2;
    private int maxNum = 50;
    private int minNum = 1;

    GuessKeyBuilder setNumNumbers (int numNumbers) {
        this.numNumbers = numNumbers;
        return this;
    }

    GuessKeyBuilder setNumStars (int numStars) {
        this.numStars = numStars;
        return this;
    }

    GuessKeyBuilder setMaxNum (int maxNum) {
        this.maxNum = maxNum;
        return this;
    }

    GuessKeyBuilder setMinNum (int minNum) {
        this.minNum = minNum;
        return this;
    }

    public GuessKey build() {
        return new GuessKey(numNumbers,numStars, maxNum, minNum);
    }
}
