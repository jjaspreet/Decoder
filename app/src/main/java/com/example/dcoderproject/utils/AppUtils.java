package com.example.dcoderproject.utils;

public class AppUtils {


    public static String getTheUsernameInitials(String username){

        char initialName = username.charAt(0);
        return  Character.toString(initialName);
    }
}
