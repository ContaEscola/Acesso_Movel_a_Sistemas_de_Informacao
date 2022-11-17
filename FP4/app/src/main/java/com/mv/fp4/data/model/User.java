package com.mv.fp4.data.model;

public class User {

    private static String email;
    private static final String[][] users = {
            {
            "pedronorberto368@gmail.com",
            "admin12345"
            },
    };

    private static void setEmail(String email) {
        User.email = email;
    }

    public static String getEmail() {
        return email;
    }

    public boolean isValidLogin(String email, String password) {
        for (String[] user : users) {
            if(email.equals(user[0]) && password.equals(user[1])){
                setEmail(email);
                return true;
            }

        }

        return false;
    }

}
