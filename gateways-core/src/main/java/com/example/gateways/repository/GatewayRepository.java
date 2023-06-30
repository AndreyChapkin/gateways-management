package com.example.gateways.repository;

import com.example.gateways.entity.GatewayEntity;
import com.example.gateways.view.LightGatewayView;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GatewayRepository extends CrudRepository<GatewayEntity, Long> {

    @Query("select COUNT(d) from DeviceEntity d where d.gateway.id = :ID")
    Integer countOfDevices(@Param("ID") Long gatewayId);

    @Query(value = "select id, name from Gateway_Entity g", nativeQuery = true)
    List<LightGatewayView> findAllLightGateways();
}
