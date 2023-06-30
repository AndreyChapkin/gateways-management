package com.example.gateways.initialization;

import com.example.gateways.dto.enumeration.StatusEnum;
import com.example.gateways.entity.DeviceEntity;
import com.example.gateways.entity.GatewayEntity;
import com.example.gateways.repository.GatewayRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@Profile("!test")
@AllArgsConstructor
public class ProdInitializer implements Initializer {

    private GatewayRepository gatewayRepository;

    @Override
    public void initialize() {
        var gateway1 = new GatewayEntity();
        gateway1.setName("Gateway 1");
        gateway1.setSerialNumber("1111-1111");
        gateway1.setIpv4Address("1.1.1.1");
        var device1 = new DeviceEntity();
        device1.setUid(1L);
        device1.setVendor("Vendor 1");
        device1.setStatus(StatusEnum.ONLINE);
        device1.setCreateDate(LocalDate.now());
        device1.setGateway(gateway1);
        var device2 = new DeviceEntity();
        device2.setUid(2L);
        device2.setVendor("Vendor 2");
        device2.setCreateDate(LocalDate.now());
        device2.setStatus(StatusEnum.ONLINE);
        device2.setGateway(gateway1);
        gateway1.setDevices(List.of(device1, device2));
        gatewayRepository.save(gateway1);
        var gateway2 = new GatewayEntity();
        gateway2.setName("Gateway 2");
        gateway2.setSerialNumber("2222-2222");
        gateway2.setIpv4Address("2.2.3.3");
        var device3 = new DeviceEntity();
        device3.setUid(1L);
        device3.setVendor("Vendor 1");
        device3.setCreateDate(LocalDate.now());
        device3.setStatus(StatusEnum.ONLINE);
        device3.setGateway(gateway2);
        var device4 = new DeviceEntity();
        device4.setUid(2L);
        device4.setVendor("Vendor 2");
        device4.setCreateDate(LocalDate.now());
        device4.setStatus(StatusEnum.ONLINE);
        device4.setGateway(gateway2);
        gateway2.setDevices(List.of(device3, device4));
        gatewayRepository.save(gateway2);
    }
}
