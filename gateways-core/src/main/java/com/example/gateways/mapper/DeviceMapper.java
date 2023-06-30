package com.example.gateways.mapper;

import com.example.gateways.dto.DeviceDto;
import com.example.gateways.entity.DeviceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    DeviceDto entityToDTO(DeviceEntity device);

    List<DeviceDto> entityToDTO(Iterable<DeviceEntity> devices);

    DeviceEntity dtoToEntity(DeviceDto deviceDto);

    List<DeviceEntity> dtoToEntity(Iterable<DeviceDto> deviceDtos);
}
