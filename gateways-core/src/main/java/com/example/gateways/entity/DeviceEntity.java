package com.example.gateways.entity;

import com.example.gateways.dto.enumeration.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DeviceEntity {

    @Id
    @SequenceGenerator(name = "todo_generator", sequenceName = "hibernate_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_generator")
    private Long id;
    private Long uid;
    private String vendor;
    private LocalDate createDate;
    private StatusEnum status;
    @ManyToOne
    @JoinColumn(name = "GATEWAY_ID", nullable = false)
    private GatewayEntity gateway;
}