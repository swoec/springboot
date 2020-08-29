package com.raken.mail.util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    @Test
    void checkSenderTest() {
        assertEquals(true, Util.checkEmail("swo@raken.com"));
    }

    @Test
    void checkSenderTestForFalse() {
        assertEquals(false, Util.checkEmail("swoecwang10@gmail.com"));
    }
}
