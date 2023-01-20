package com.mv.fp9.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferencesHelper {
    private static PreferencesHelper instance = null;

    private static final String PREF_USER = "PREF_USER";
    private static final String PREF_KEY_EMAIL = "PREF_KEY_EMAIL";
    private static final String PREF_KEY_TOKEN = "PREF_KEY_TOKEN";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor preferencesEditor;

    private PreferencesHelper(Context context) {
        this.sharedPreferences = context.getSharedPreferences(PREF_USER,Context.MODE_PRIVATE);
        this.preferencesEditor = sharedPreferences.edit();
    }

    public static synchronized PreferencesHelper getInstance(Context context) {
        if(instance == null)
            instance = new PreferencesHelper(context);

        return instance;
    }

    public void setEmailPreference(String emailPreference) {
        preferencesEditor.putString(PREF_KEY_EMAIL, emailPreference).commit();
    }

    public void setTokenPreference(String tokenPreference) {
        preferencesEditor.putString(PREF_KEY_TOKEN, tokenPreference).commit();
    }

    public String getEmailPreference() {
        return sharedPreferences.getString(PREF_KEY_EMAIL, "");
    }

    public String getTokenPreference() { return sharedPreferences.getString(PREF_KEY_TOKEN, ""); }

}
