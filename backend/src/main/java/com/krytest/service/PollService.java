package com.krytest.service;

import com.krytest.entity.Endpoint;
import com.krytest.repository.EndpointRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class PollService {

    Logger logger = LoggerFactory.getLogger(PollService.class);

    @Autowired
    private final EndpointRepository endpointRepository;
    private final OkHttpClient client;

    public PollService(EndpointRepository endpointRepository) {
        this.endpointRepository = endpointRepository;
        this.client = new OkHttpClient();
    }

    private int getStatus(String url) {
        int statusCode = 0;
        Request request = new Request.Builder().url(url).build();
        try (Response response = client.newCall(request).execute()) {
            statusCode = response.code();
            logger.info("\r\nUrl: " + url + " \r\nStatus: " + statusCode + "\r\n");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return statusCode;
    }

    public void updateData() throws IOException {
        List<Endpoint> endpoints = endpointRepository.findAll();
        for (Endpoint endpoint : endpoints) {
            int status = getStatus(endpoint.getUrl());
            endpoint.setActive(status == 200);
            endpoint.setUpdated(new Date());
            if (endpointRepository.existsById(endpoint.getId())) {
                endpointRepository.save(endpoint);
            }
        }
    }
}
