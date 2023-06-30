package com.example.gateways.repository;

import com.example.gateways.entity.DeviceEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface DeviceRepository extends CrudRepository<DeviceEntity, Long> {

    @Modifying
    @Query("delete from DeviceEntity where id = :ID")
    void deleteById(@Param("ID") Long id);
}
