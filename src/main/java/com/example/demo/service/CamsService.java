package com.example.demo.service;

import com.example.demo.entity.Cams;
import reactor.core.publisher.Flux;

public interface CamsService {
    Flux<Cams> concatCamsDataFromRemoteClient();
}
