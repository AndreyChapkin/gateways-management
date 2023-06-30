package com.example.gateways.repository;

import com.example.gateways.entity.DeviceEntity;
import com.example.gateways.entity.GatewayEntity;
import org.springframework.data.repository.CrudRepository;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {
}
