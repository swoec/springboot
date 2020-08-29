package com.raken.mail.template;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HtmlTemplateTest {
    @Test
    public void checkImgTemplate (){
        String img = "<img src='link' width='50' height='50'/>";
        assertEquals(img, HtmlTemplate.imgTemplate("link"));
    }

  }
