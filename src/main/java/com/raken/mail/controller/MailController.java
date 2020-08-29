package com.raken.mail.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.raken.mail.filter.SenderFilter;
import com.raken.mail.model.Emails;
import com.raken.mail.model.Photo;
import com.raken.mail.service.MailService;
import com.raken.mail.service.PhotoService;
import com.raken.mail.template.HtmlTemplate;

@RestController
public class MailController {
    private static final Logger logger = LoggerFactory.getLogger(MailController.class);

    @Autowired
    private MailService mailService;

    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/sendgrid", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public String SendEmailWithSendGrid(@RequestParam("enrich") boolean enrich, @RequestBody Emails emails) throws JsonProcessingException {

        if (!SenderFilter.checkSender(emails.getSender())) {
            logger.warn("Caution! the email from outside!-----" + emails.getSender());
        }
        if (enrich) {
            Photo newPh = photoService.getPhoto(1);
            emails.getBody().append(HtmlTemplate.imgTemplate(newPh.getUrl()));
        }

        boolean sent = mailService.sendMail(emails);

        if (!sent) {
            return "{\"status\":400,\"message\":\"send fail!\"}";
        }

        return "{\"status\":200, \"message\":\"send successful.\"}";
    }
}
