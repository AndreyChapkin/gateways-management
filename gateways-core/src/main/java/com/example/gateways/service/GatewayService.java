package com.example.gateways.service;

import com.example.gateways.dto.GatewayDto;
import com.example.gateways.dto.LightGatewayDto;
import com.example.gateways.mapper.GatewayMapper;
import com.example.gateways.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class GatewayService {

    private GatewayRepository gatewayRepository;
    private GatewayMapper gatewayMapper;

    public void createGateway(GatewayDto gatewayDto) {
        var entity = gatewayMapper.dtoToEntity(gatewayDto);
        gatewayRepository.save(entity);
    }

    public GatewayDto findGateway(Long id) {
        var entity = gatewayRepository.findById(id);
        return entity
            .map(gatewayMapper::entityToDTO)
            .orElse(null);
    }

    public List<LightGatewayDto> getAllGateways() {
        var entities = gatewayRepository.findAllLightGateways();
        return entities.stream()
            .map(gatewayMapper::lightEntityToLightDTO)
            .collect(Collectors.toList());
    }

    public void deleteGateway(Long id) {
        gatewayRepository.deleteById(id);
    }
}
