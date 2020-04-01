package com.timecounter.deviceservice.web.handler;

import com.timecounter.deviceservice.dto.DevicePostRequest;
import com.timecounter.deviceservice.dto.DevicePutRequest;
import com.timecounter.deviceservice.service.DeviceService;
import com.timecounter.deviceservice.web.router.DeviceRouter;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;

@Component
public class DeviceHandlerImpl implements DeviceHandler {

    private final DeviceService deviceService;

    public DeviceHandlerImpl(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public Mono<ServerResponse> createNewDevice(ServerRequest request) {
        return request.bodyToMono(DevicePostRequest.class)
                .flatMap(deviceService::createDevice)
                .flatMap(device ->
                        ServerResponse.created(URI.create(DeviceRouter.DEVICE_URL + "/" + device.getId()))
                                .build());
    }

    @Override
    public Mono<ServerResponse> updateDevice(ServerRequest request) {
        return request.bodyToMono(DevicePutRequest.class)
                .map(device -> deviceService.updateDevice(device, request.pathVariable("id")))
                .flatMap(dto -> ServerResponse.noContent().build());
    }
}
