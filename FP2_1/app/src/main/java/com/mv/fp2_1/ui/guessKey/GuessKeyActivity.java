package com.mv.fp2_1.ui.guessKey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mv.fp2_1.R;
import com.mv.fp2_1.data.models.GuessKey;
import com.mv.fp2_1.data.models.GuessKeyBuilder;
import com.mv.fp2_1.utils.InputUtils;

import java.util.HashMap;
import java.util.Map;

public class GuessKeyActivity extends AppCompatActivity {

    private final GuessKeyMvpPresenter mPresenter;
    private TextView tvKey;
    private TextView tvResult;

    private EditText etFirstNum;
    private EditText etSecondNum;
    private EditText etThirdNum;
    private EditText etFourthNum;
    private EditText etFifthNum;

    private EditText etFirstStar;
    private EditText etSecondStar;

    private Button btnGenerateKey;

    public TextView getTvKey() {
        return tvKey;
    }

    public TextView getTvResult() {
        return tvResult;
    }

    public GuessKeyActivity() {
        mPresenter = new GuessKeyPresenter(
                new GuessKeyView(this),
                new GuessKeyBuilder().build()
        );
    }



    private void setTextViews(){
        tvKey =  findViewById(R.id.tvKey);
        tvResult = findViewById(R.id.tvResult);
    }



    private void setEditTexts(){
        etFirstNum = findViewById(R.id.etFirstNum);
        etSecondNum = findViewById(R.id.etSecondNum);
        etThirdNum = findViewById(R.id.etThirdNum);
        etFourthNum = findViewById(R.id.etFourthNum);
        etFifthNum = findViewById(R.id.etFifthNum);

        etFirstStar =  findViewById(R.id.etFirstStar);
        etSecondStar =  findViewById(R.id.etSecondStar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTextViews();
        setEditTexts();
        btnGenerateKey = findViewById(R.id.btnGenerateKey);

        btnGenerateKey.setOnClickListener(view -> {

            EditText[] etNumbersArray = new EditText[] {
                    etFirstNum,
                    etSecondNum,
                    etThirdNum,
                    etFourthNum,
                    etFifthNum
            };

            EditText[] etStarsArray = new EditText[] {
                    etFirstStar,
                    etSecondStar
            };

            EditText emptyInputNumber = InputUtils.EditTexts.isEmpty(etNumbersArray);

            if(emptyInputNumber != null) {
                emptyInputNumber.requestFocus();
                Log.d("EmptyInput", getResources().getResourceEntryName(emptyInputNumber.getId()));
                Toast.makeText(getApplicationContext(), "Digite todos os números!", Toast.LENGTH_SHORT).show();
                return;
            }

            EditText emptyInputStar = InputUtils.EditTexts.isEmpty(etStarsArray);

            if(emptyInputStar != null) {
                emptyInputStar.requestFocus();
                Log.d("EmptyInput", getResources().getResourceEntryName(emptyInputStar.getId()));
                Toast.makeText(getApplicationContext(), "Digite todas as estrelas!", Toast.LENGTH_SHORT).show();
                return;
            }

            int[] numbers = new int[etNumbersArray.length];
            int[] stars = new int[etStarsArray.length];

            for(int i = 0; i < etNumbersArray.length; i++) {
                numbers[i] = Integer.parseInt(etNumbersArray[i].getText().toString());
            }

            for(int i = 0; i < etStarsArray.length; i++) {
                stars[i] = Integer.parseInt(etStarsArray[i].getText().toString());
            }

            mPresenter.onGuess(numbers, stars);

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.terminate();
    }
}