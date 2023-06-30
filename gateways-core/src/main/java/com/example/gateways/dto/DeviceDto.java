package com.example.gateways.dto;

import com.example.gateways.dto.enumeration.StatusEnum;

import java.time.LocalDate;

public record DeviceDto(
    Long id,
    Long uid,
    String vendor,
    LocalDate createDate,
    StatusEnum status
) {
}
