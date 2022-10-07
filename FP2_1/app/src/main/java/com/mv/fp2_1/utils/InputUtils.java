package com.mv.fp2_1.utils;

import android.widget.EditText;

public class InputUtils {

    public static class EditTexts{

        public static EditText isEmpty(EditText[] editTexts) {
            for (EditText editText : editTexts) {
                if(editText.getText().toString().isEmpty())
                    return editText;
            }
            return null;
        }

        public static EditText isEmpty(EditText editText) {

            if(editText.getText().toString().isEmpty())
                    return editText;

            return null;
        }
    }

}
