package com.example.gateways.dto;


import jakarta.validation.constraints.Pattern;

import java.util.List;

public record GatewayDto(
    Long id,
    String serialNumber,
    String name,
    @Pattern(regexp = "^(?:[0-9]{1,3}\\.){3}[0-9]{1,3}$")
    String ipv4Address,
    List<DeviceDto> devices
) {
}
