package com.example.gateways;

import com.example.gateways.entity.DeviceEntity;
import com.example.gateways.repository.DeviceRepository;
import com.example.gateways.repository.GatewayRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class DeviceTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @MockBean
    private DeviceRepository deviceRepository;
    @MockBean
    private GatewayRepository gatewayRepository;

    @Test
    public void shouldReturnDevice() throws Exception {
        var deviceEntity = new DeviceEntity();
        deviceEntity.setVendor("Vendor 1");
        when(deviceRepository.findById(anyLong())).thenReturn(Optional.of(deviceEntity));
        this.mockMvc.perform(get("/api/device/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Vendor 1")));
    }

    @Test
    public void shouldCreateDevice() throws Exception {
        when(gatewayRepository.existsById(1L)).thenReturn(true);
        when(gatewayRepository.countOfDevices(1L)).thenReturn(2);
        var deviceEntity = new DeviceEntity();
        deviceEntity.setVendor("Vendor 1");
        this.mockMvc.perform(post("/api/device?gatewayId=1")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(deviceEntity)))
            .andExpect(status().isOk());
        verify(deviceRepository, times(1)).save(any());
    }

    @Test
    public void shouldDeleteDevice() throws Exception {
        this.mockMvc.perform(delete("/api/device/1"))
            .andExpect(status().isOk());
        verify(deviceRepository, times(1)).deleteById(anyLong());
    }
}
