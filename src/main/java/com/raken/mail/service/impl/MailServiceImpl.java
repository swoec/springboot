package com.raken.mail.service.impl;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.raken.mail.model.Emails;
import com.raken.mail.service.MailService;


import java.io.IOException;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private SendGrid sendGrid;

    @Value("${templateId}")
    private String EMAIL_TEMPLATE_ID;

    @Override
    public boolean sendMail(Emails emails) {

        Email from = new Email(emails.getSender());
        String subject = emails.getTitle();
        Email to = new Email(emails.getReceiver());
        Content content = new Content("text/html", emails.getBody().toString());

        Mail mail = new Mail(from, subject, to, content);
        mail.setReplyTo(from);
        mail.personalization.get(0).addSubstitution("-username-", "Alex Wang");
        if (emails.getCc()!=null && emails.getCc().length >= 1) {
            for (String cc : emails.getCc()) {
                mail.personalization.get(0).addCc(new Email(cc));
            }
        }

        Request request = new Request();
        Response response;

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            response = sendGrid.api(request);

            if (response.getStatusCode() == 202) {
                return true;
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            return false;
        }

        return false;
    }


}
