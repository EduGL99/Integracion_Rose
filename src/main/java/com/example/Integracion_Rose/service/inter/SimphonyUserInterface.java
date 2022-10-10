package com.example.Integracion_Rose.service.inter;

import com.example.Integracion_Rose.model.symphony.v6.graphql.UserTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

public interface SimphonyUserInterface {

    UserTO get(String id) throws HttpClientErrorException, HttpServerErrorException, ResourceAccessException,
            JsonProcessingException;
}
