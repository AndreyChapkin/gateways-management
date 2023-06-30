package com.example.gateways.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class GatewayEntity {
    @Id
    @SequenceGenerator(name = "todo_generator", sequenceName = "hibernate_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_generator")
    private Long id;
    private String serialNumber;
    private String name;
    private String ipv4Address;
    @OneToMany(mappedBy = "gateway", cascade = {CascadeType.ALL})
    @Fetch(FetchMode.JOIN)
    private List<DeviceEntity> devices;
}
