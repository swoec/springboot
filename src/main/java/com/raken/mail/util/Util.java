package com.raken.mail.util;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    public static boolean checkEmail(String email) {

        String regex = "^(.+)@raken.com$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
}
