package com.example.demo.api.controller;

import com.example.demo.entity.Cams;
import com.example.demo.service.CamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequiredArgsConstructor
public class CamsInfo {
    private final CamsService service;

    @GetMapping(value = "/get-cams", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Cams> getCams() {
        return service.concatCamsDataFromRemoteClient();
    }
}
