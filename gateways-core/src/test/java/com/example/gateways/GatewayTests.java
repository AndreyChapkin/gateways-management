package com.example.gateways;


import com.example.gateways.entity.GatewayEntity;
import com.example.gateways.repository.GatewayRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(value = "test")
class GatewayTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private GatewayRepository gatewayRepository;

    @Test
    public void shouldReturnGateway() throws Exception {
        var gatewayEntity = new GatewayEntity();
        gatewayEntity.setName("Gateway 1");
        when(gatewayRepository.findById(anyLong())).thenReturn(Optional.of(gatewayEntity));
        this.mockMvc.perform(get("/api/gateway/1"))
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Gateway 1")));
    }

    @Test
    public void shouldCreateGateway() throws Exception {
        var gatewayEntity = new GatewayEntity();
        gatewayEntity.setName("Gateway 1");
        this.mockMvc.perform(post("/api/gateway")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(gatewayEntity)))
            .andExpect(status().isOk());
        verify(gatewayRepository, times(1)).save(any());
    }

    @Test
    public void shouldDeleteGateway() throws Exception {
        this.mockMvc.perform(delete("/api/gateway/1"))
            .andExpect(status().isOk());
        verify(gatewayRepository, times(1)).deleteById(anyLong());
    }
}
