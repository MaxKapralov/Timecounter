package com.timecounter.deviceservice.web.handler;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface DeviceHandler {
    Mono<ServerResponse> createNewDevice(ServerRequest request);
    Mono<ServerResponse> updateDevice(ServerRequest request);
}
