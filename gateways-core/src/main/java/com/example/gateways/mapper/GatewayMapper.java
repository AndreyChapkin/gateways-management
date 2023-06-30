package com.example.gateways.mapper;

import com.example.gateways.dto.GatewayDto;
import com.example.gateways.dto.LightGatewayDto;
import com.example.gateways.entity.GatewayEntity;
import com.example.gateways.view.LightGatewayView;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GatewayMapper {
    GatewayDto entityToDTO(GatewayEntity gateway);

    LightGatewayDto lightEntityToLightDTO(LightGatewayView gateway);

    List<GatewayDto> entityToDTO(Iterable<GatewayEntity> gateways);

    GatewayEntity dtoToEntity(GatewayDto gatewayDto);

    List<GatewayEntity> dtoToEntity(Iterable<GatewayDto> gatewayDtos);
}
