package com.example.demo.service;

import com.example.demo.TestSupportForServicesAndControllers;
import com.example.demo.client.MockyClient;
import com.example.demo.entity.Cams;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.context.annotation.Import;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import static com.example.demo.entity.CamsType.LIVE;

@Import(RemoteCamsService.class)
public class RemoteCamsServiceTest extends TestSupportForServicesAndControllers {
    @Autowired
    private RemoteCamsService subj;

    @SpyBean
    private MockyClient mockyClient;

    private Cams first;

    @Before
    public void setUp() throws Exception {
        first = Cams.builder()
                .id(2L)
                .urlType(LIVE)
                .videoUrl("rtsp://127.0.0.1/20")
                .value("fa4b5f64-249b-11e9-ab14-d663bd873d93")
                .ttl("180")
                .build();
    }

    @Test
    public void loadCams() {
        Flux<Cams> camsFlux = subj.concatCamsDataFromRemoteClient();

        StepVerifier.create(camsFlux)
                .expectNext(first)
                .expectNextCount(3L)
                .verifyComplete();
    }
}