package com.raken.mail.service;
import com.raken.mail.model.Emails;

public interface MailService {

    public abstract boolean sendMail(Emails emails);
}
