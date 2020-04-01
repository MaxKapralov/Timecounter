package com.timecounter.deviceservice.web.router;

import com.timecounter.deviceservice.web.handler.DeviceHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class DeviceRouter {

    public static final String DEVICE_URL = "/devices";

    @Bean
    public RouterFunction<ServerResponse> routes(DeviceHandler handler) {
        return RouterFunctions.route(POST(DEVICE_URL).and(accept(MediaType.APPLICATION_JSON)), handler::createNewDevice)
                .andRoute(PUT(DEVICE_URL + "/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::updateDevice);
    }
}
