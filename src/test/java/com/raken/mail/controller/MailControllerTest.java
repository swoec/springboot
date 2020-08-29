package com.raken.mail.controller;

import com.raken.mail.model.Emails;
import com.raken.mail.model.Photo;
import com.raken.mail.service.MailService;
import com.raken.mail.service.PhotoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@AutoConfigureJsonTesters
@WebMvcTest(MailController.class)
@AutoConfigureMockMvc
@EnableWebMvc
class MailControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private MailService mailService;

    @MockBean
    private PhotoService photoService;
    @Autowired
    private JacksonTester jsonSend;

    private Emails emails;

    private Photo photo;

    @BeforeEach
    public void initEmails() {
        emails = new Emails();
        emails.setSender("swoec@raken.com");
        emails.setBody(new StringBuilder("body"));
        emails.setTitle("title");
        emails.setReceiver("sw@gmail.com");
        String[] cc = {"swoecwang@gmail.com", "swoec.wang@gmail.com"};
        emails.setCc(cc);

        photo = new Photo();
        photo.setAlbumId(1);
        photo.setId(1);
        photo.setTitle("accusamus beatae ad facilis cum similique qui sunt");
        photo.setUrl("https://via.placeholder.com/600/92c952");
        photo.setThumbnailUrl("https://via.placeholder.com/150/92c952");
    }

    @Test
    public void testInput() throws Exception {

        given(mailService.sendMail(emails)).willReturn(true);
        given(photoService.getPhoto(1)).willReturn(photo);
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

        MockHttpServletResponse response = this.mockMvc.perform(
                post("/sendgrid")
                        .accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8).param("enrich", "true").content(jsonSend.write(emails).getJson())).andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ArgumentCaptor<Emails> emailsCaptor = ArgumentCaptor.forClass(Emails.class);
        verify(mailService, times(1)).sendMail(emailsCaptor.capture());
        assertThat(emailsCaptor.getValue().getSender()).isEqualTo("swoec@raken.com");
        assertThat(emailsCaptor.getValue().getReceiver()).isEqualTo("sw@gmail.com");
        assertThat(emailsCaptor.getValue().getTitle()).isEqualTo("title");

    }

    @Test
    public void getSuccessMessageFromService() throws Exception {

        when(mailService.sendMail(any(Emails.class))).thenReturn(true);
        given(photoService.getPhoto(1)).willReturn(photo);
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

        MockHttpServletResponse response = this.mockMvc.perform(
                post("/sendgrid")
                        .accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8).param("enrich", "true").content(jsonSend.write(emails).getJson())).andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ArgumentCaptor<Emails> emailsCaptor = ArgumentCaptor.forClass(Emails.class);
        verify(mailService, times(1)).sendMail(emailsCaptor.capture());

        assertThat(response.getContentAsString()).contains("send successful.");


    }

    @Test
    public void getFailMessage() throws Exception {

        when(mailService.sendMail(any(Emails.class))).thenReturn(false);
        given(photoService.getPhoto(1)).willReturn(photo);
        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

        MockHttpServletResponse response = this.mockMvc.perform(
                post("/sendgrid")
                        .accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8).param("enrich", "true").content(jsonSend.write(emails).getJson())).andDo(print())
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

        ArgumentCaptor<Emails> emailsCaptor = ArgumentCaptor.forClass(Emails.class);
        verify(mailService, times(1)).sendMail(emailsCaptor.capture());

        assertThat(response.getContentAsString()).contains("send fail!");

    }


    @Test
    public void testEnrich() throws Exception {

        MediaType MEDIA_TYPE_JSON_UTF8 = new MediaType("application", "json", java.nio.charset.Charset.forName("UTF-8"));

        MockHttpServletResponse response = this.mockMvc.perform(
                post("/sendgrid")
                        .accept(MEDIA_TYPE_JSON_UTF8).contentType(MEDIA_TYPE_JSON_UTF8).param("enrich", "false").content(jsonSend.write(emails).getJson())).andDo(print())
                .andReturn().getResponse();

        ArgumentCaptor<Photo> photoCaptor = ArgumentCaptor.forClass(Photo.class);
        verify(photoService, times(0)).getPhoto(1);


    }


}
