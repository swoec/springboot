package com.raken.mail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raken.mail.model.Photo;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PhotoServiceTest {
    @Mock
    private PhotoService photoService;

    @Test
    public void getPhoto() throws JsonProcessingException {
        Photo photo = new Photo();
        photo.setAlbumId(1);
        photo.setId(1);
        photo.setTitle("accusamus beatae ad facilis cum similique qui sunt");
        photo.setUrl("https://via.placeholder.com/600/92c952");
        photo.setThumbnailUrl("https://via.placeholder.com/150/92c952");
        when(this.photoService.getPhoto(1)).thenReturn(photo);
        assertEquals(photo, this.photoService.getPhoto(1));

    }
}
