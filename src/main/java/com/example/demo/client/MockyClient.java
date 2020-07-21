package com.example.demo.client;

import com.example.demo.client.dto.CamsDTO;
import com.example.demo.client.dto.CamsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MockyClient {
    private final WebClient client;

    @Value("${cams.url}")
    private String url;

    public Mono<List<CamsDTO>> loadCams() {
        return client
                .get()
                .uri(url)
                .exchange()
                .flatMap(res -> res.bodyToMono(CamsResponse.class));
    }
}
