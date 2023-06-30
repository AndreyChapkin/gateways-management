package com.example.gateways.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDto {

    private String message;
    private Long code;
}
