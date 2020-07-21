package com.example.demo.api.controller;

import com.example.demo.TestSupportForServicesAndControllers;
import com.example.demo.entity.Cams;
import com.example.demo.service.RemoteCamsService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class CamsInfoTest extends TestSupportForServicesAndControllers {
    @Autowired
    private CamsInfo subj;

    @MockBean
    private RemoteCamsService service;

    @Before
    public void setUp() throws Exception {
        client = WebTestClient
                .bindToController(subj)
                .build();
    }

    @Test
    public void getCams() {
        client.get()
                .uri("/get-cams")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().valueEquals("Content-Type", "application/stream+json")
                .expectBody(Cams.class);

        verify(service, times(1)).concatCamsDataFromRemoteClient();
    }
}