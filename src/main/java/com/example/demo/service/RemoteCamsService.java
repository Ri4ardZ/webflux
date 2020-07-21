package com.example.demo.service;

import com.example.demo.client.MockyClient;
import com.example.demo.entity.Cams;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class RemoteCamsService implements CamsService {
    private final MockyClient mockyClient;
    private final WebClient client;

    @Override
    public Flux<Cams> concatCamsDataFromRemoteClient() {
        return mockyClient.loadCams()
                .flatMapIterable(res -> res)
                .flatMap(response1 -> Flux.concat(getData(response1.getSourceDataUrl()), getData(response1.getTokenDataUrl()))
                        .reduce((response2, response3) ->
                                Cams.builder()
                                        .id(response1.getId())
                                        .urlType(response2.getUrlType())
                                        .videoUrl(response2.getVideoUrl())
                                        .value(response3.getValue())
                                        .ttl(response3.getTtl())
                                        .build()));

    }

    private Mono<Cams> getData(String uri) {
        return client
                .get()
                .uri(uri)
                .retrieve()
                .bodyToMono(Cams.class);
    }
}
