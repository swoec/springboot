package com.raken.mail.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.raken.mail.model.Photo;

public interface PhotoService {

    public Photo getPhoto(int id) throws JsonProcessingException;
}
