package com.example.fp2.ui.guessNumber;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fp2.R;
import com.example.fp2.data.models.GuessNumberBuilder;

public class GuessNumberActivity extends AppCompatActivity {

    // Declarar o MvpPresenter
    private GuessNumberMvpPresenter mPresenter;

    private TextView tvResult;
    private EditText etGuessNumber;


    public TextView getTvResult() {
        return tvResult;
    }

    public EditText getEtGuessNumber() {
        return etGuessNumber;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = findViewById(R.id.tvResult);
        etGuessNumber = findViewById(R.id.etGuessNumber);

        // Inicializar um novo MvpPresenter com uma nova View e um novo Model, a nova view refere-se à rule 5 do MVP
        mPresenter = new GuessNumberPresenter(
                new GuessNumberView(this),
                new GuessNumberBuilder().build()
        );

    }

    public void btnGuess_OnClick(View view) {
        String guessNumber = String.valueOf(etGuessNumber.getText());


        if(guessNumber.isEmpty()) {
            Toast.makeText(this,"Escreva um número!", Toast.LENGTH_LONG);
            return;
        }

        int guessNumberInt = Integer.parseInt(guessNumber);

        // Chama a ação onGuessing do Presenter
        mPresenter.onGuessing(guessNumberInt);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.terminate();
    }


}