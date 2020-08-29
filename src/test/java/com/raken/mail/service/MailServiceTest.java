package com.raken.mail.service;

import com.raken.mail.model.Emails;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MailServiceTest {

    @Mock
    private MailService mailService;

    @Test
    public void getSuccessResponse() {

        Emails emails = new Emails();
        emails.setSender("swoec@raken.com");
        emails.setBody(new StringBuilder("body"));
        emails.setTitle("title");
        emails.setReceiver("sw@gmail.com");
        when(this.mailService.sendMail(emails)).thenReturn(true);
        assertEquals(true, this.mailService.sendMail(emails));


    }

    @Test
    public void getFailureResponse() {
        Emails emails = new Emails();
        when(this.mailService.sendMail(emails)).thenReturn(false);
        assertEquals(false, this.mailService.sendMail(emails));
    }
}
