package com.example.gateways.controller;

import com.example.gateways.dto.GatewayDto;
import com.example.gateways.dto.LightGatewayDto;
import com.example.gateways.service.GatewayService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gateway")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class GatewayController {

    private GatewayService gatewayService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createGateway(@RequestBody @Valid GatewayDto gatewayDto) {
        gatewayService.createGateway(gatewayDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GatewayDto findGateway(@PathVariable("id") Long id) {
        return gatewayService.findGateway(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<LightGatewayDto> getAllGateways() {
        return gatewayService.getAllGateways();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteGateway(@PathVariable("id") Long id) {
        gatewayService.deleteGateway(id);
    }
}
