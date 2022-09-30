package com.example.fp2.data.models;


// Usei o Builder pattern da 2º resposta com 610 upvotes
// https://stackoverflow.com/questions/997482/does-java-support-default-parameter-values
public class GuessNumberBuilder {

    // Defaults máximos e minimos para quando gerar um número.
    private int maxNumber = 10;
    private int minNumber = 1;

    public GuessNumberBuilder setMaxNumber(int maxNumber){
        this.maxNumber = maxNumber;
        return this;
    }

    public GuessNumberBuilder setMinNumber(int minNumber){
        this.minNumber = minNumber;
        return this;
    }

    public GuessNumber build() {
        return new GuessNumber(maxNumber, minNumber);
    }
}
