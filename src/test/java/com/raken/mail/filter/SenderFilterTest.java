package com.raken.mail.filter;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SenderFilterTest {

    @Test
    void checkSenderTest() {
        assertEquals(true, SenderFilter.checkSender("swoecwang@raken.com"));
    }

    @Test
    void checkSenderTestForFalse() {
        assertEquals(false, SenderFilter.checkSender("swoecwang10@gmail.com"));
    }
}
