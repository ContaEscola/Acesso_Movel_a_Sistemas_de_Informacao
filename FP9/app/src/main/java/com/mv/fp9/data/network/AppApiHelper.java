package com.mv.fp9.data.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.mv.fp9.data.db.model.Book;
import com.mv.fp9.data.db.model.SingletonBookManager;
import com.mv.fp9.listeners.BooksListener;
import com.mv.fp9.listeners.LoginListener;
import com.mv.fp9.ui.adapters.RecyclerViewAdapter;
import com.mv.fp9.utils.NetworkUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class AppApiHelper {

    private static AppApiHelper instance = null;
    private Context context;

    private static RequestQueue volleyQueue;

    private LoginListener loginListener;

    public void setLoginListener(LoginListener loginListener) {
        this.loginListener = loginListener;
    }

    private AppApiHelper(Context context) {
        this.context = context;

        volleyQueue = Volley.newRequestQueue(context.getApplicationContext());

    }

    public static synchronized AppApiHelper getInstance(Context context){
        if (instance == null) {
            instance = new AppApiHelper(context);
        }

        return instance;
    }

    public void authLoginApi(final String email, final String password) {
        if(!NetworkUtils.hasInternetConnection(context)) return;
        Map<String, Object> jsonParams = new HashMap<>();
        jsonParams.put("email", email);
        jsonParams.put("password", password);

        JsonObjectRequest jsonAuthLoginReq = new JsonObjectRequest(Request.Method.POST, ApiEndPoints.ENDPOINT_AUTH_LOGIN, new JSONObject(jsonParams), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(response.getString("success").equals("false")) {
                        loginListener.onInvalidLogin("Login inválido!",context);
                        return;
                    }
                    loginListener.onValidLogin(response.getString("token"), email, context);



                } catch (JSONException e) {
                    Log.d("ApiError", "Auth Login Error:" + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ApiError", "Auth Login Error:" + error.getMessage());
            }
        });

        volleyQueue.add(jsonAuthLoginReq);
    }

}
