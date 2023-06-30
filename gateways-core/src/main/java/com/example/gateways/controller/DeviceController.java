package com.example.gateways.controller;

import com.example.gateways.dto.DeviceDto;
import com.example.gateways.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/device")
@AllArgsConstructor
@CrossOrigin("http://localhost:3000")
public class DeviceController {

    private DeviceService deviceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createDevice(@RequestParam("gatewayId") Long gatewayId, @RequestBody DeviceDto DeviceDto) {
        deviceService.createDevice(gatewayId, DeviceDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceDto findDevice(@PathVariable("id") Long id) {
        return deviceService.findDevice(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceDto> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDevice(@PathVariable("id") Long id) {
        deviceService.deleteDevice(id);
    }
}
