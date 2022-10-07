package com.mv.fp2_1.ui.guessKey;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mv.fp2_1.R;
import com.mv.fp2_1.data.models.GuessKey;
import com.mv.fp2_1.utils.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GuessKeyActivity extends AppCompatActivity {

    private final GuessKeyMvpPresenter mPresenter;
    private final Map<String, TextView> textViews;

    private final Map<String, EditText> editTextsNumbers;
    private final Map<String, EditText> editTextsStars;
    private Button btnGenerateKey;

    public GuessKeyActivity() {
        mPresenter = new GuessKeyPresenter(
                new GuessKeyView(this),
                new GuessKey()
        );

        textViews = new HashMap<>();
        editTextsNumbers = new HashMap<>();
        editTextsStars = new HashMap<>();
    }

    private void setTextViews(){
        textViews.put("tvKey", (TextView) findViewById(R.id.tvKey));
        textViews.put("tvResult", (TextView) findViewById(R.id.tvResult));
    }

    public TextView getTextView(String key) {
        return textViews.get(key);
    }

    private void setEditTexts(){
        editTextsNumbers.put("etFirstNum", (EditText) findViewById(R.id.etFirstNum));
        editTextsNumbers.put("etSecondNum", (EditText) findViewById(R.id.etSecondNum));
        editTextsNumbers.put("etThirdNum", (EditText) findViewById(R.id.etThirdNum));
        editTextsNumbers.put("etFourthNum", (EditText) findViewById(R.id.etFourthNum));
        editTextsNumbers.put("etFifthNum", (EditText) findViewById(R.id.etFifthNum));

        editTextsStars.put("etFirstStar", (EditText) findViewById(R.id.etFirstStar));
        editTextsStars.put("etSecondStar", (EditText) findViewById(R.id.etSecondStar));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        setTextViews();
        setEditTexts();
        btnGenerateKey = findViewById(R.id.btnGenerateKey);

        btnGenerateKey.setOnClickListener(view -> {

            EditText[] etNumbersArray = editTextsNumbers.values().toArray(new EditText[0]);
            EditText[] etStarsArray = editTextsStars.values().toArray(new EditText[0]);

            EditText emptyInputNumber = InputUtils.EditTexts.isEmpty(etNumbersArray);

            if(emptyInputNumber != null) {
                emptyInputNumber.requestFocus();
                Toast.makeText(getApplicationContext(), "Digite todos os números!", Toast.LENGTH_LONG);
            }

            EditText emptyInputStar = InputUtils.EditTexts.isEmpty(etStarsArray);

            if(emptyInputStar != null) {
                emptyInputStar.requestFocus();
                Toast.makeText(getApplicationContext(), "Digite todas as estrelas!", Toast.LENGTH_LONG);
            }

            List<Integer> numbers = new ArrayList<Integer>();
            for(EditText editText : etNumbersArray) {
                numbers[] = Integer.parseInt(editText.getText().toString());
            }
            int[] stars;


           //mPresenter.onGuess();
        });
    }




}