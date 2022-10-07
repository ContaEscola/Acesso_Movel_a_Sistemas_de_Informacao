package com.mv.fp2_1.utils;


import android.util.Log;
import android.widget.EditText;


public class InputUtils {

    public static class EditTexts{

        public static EditText isEmpty(EditText[] editTexts) {
            EditText toReturn = null;

            for (EditText editText : editTexts) {
                if(editText.getText().toString().isEmpty()) {
                    toReturn = editText;
                    break;


                }

            }


            return toReturn;
        }

        public static EditText isEmpty(EditText editText) {
            EditText toReturn = null;

            if(editText.getText().toString().isEmpty())
                toReturn =  editText;

            return toReturn;
        }
    }

}
