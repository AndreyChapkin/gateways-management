package com.example.gateways.service;

import com.example.gateways.dto.DeviceDto;
import com.example.gateways.entity.GatewayEntity;
import com.example.gateways.mapper.DeviceMapper;
import com.example.gateways.repository.DeviceRepository;
import com.example.gateways.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class DeviceService {

    private DeviceRepository deviceRepository;
    private GatewayRepository gatewayRepository;
    private DeviceMapper deviceMapper;

    public void createDevice(Long gatewayId, DeviceDto deviceDto) {
        var gatewayExists = gatewayRepository.existsById(gatewayId);
        if (gatewayExists) {
            var devicesCount = gatewayRepository.countOfDevices(gatewayId);
            if (devicesCount <= 10) {
                var entity = deviceMapper.dtoToEntity(deviceDto);
                entity.setCreateDate(LocalDate.now());
                var gateway = new GatewayEntity();
                gateway.setId(gatewayId);
                entity.setGateway(gateway);
                deviceRepository.save(entity);
            }
        }
    }

    public DeviceDto findDevice(Long id) {
        var entity = deviceRepository.findById(id);
        return entity
            .map(deviceMapper::entityToDTO)
            .orElse(null);
    }

    public List<DeviceDto> getAllDevices() {
        var entities = deviceRepository.findAll();
        return StreamSupport.stream(entities.spliterator(), false)
            .map(deviceMapper::entityToDTO)
            .collect(Collectors.toList());
    }

    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }
}
