package com.raken.mail.filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.raken.mail.util.Util;

public class SenderFilter {
    private static final Logger logger = LoggerFactory.getLogger(SenderFilter.class);

    public static boolean checkSender(String email) {
        return Util.checkEmail(email);
    }
}
