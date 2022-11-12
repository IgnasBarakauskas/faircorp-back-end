package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.HeaterDao;
import com.emse.spring.faircorp.model.Heater;
import com.emse.spring.faircorp.model.HeaterStatus;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
public class HeaterControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private HeaterDao heaterDao;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldCreateHeater() throws Exception {
        mockMvc.perform(post("/api/heaters").contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "  \"heaterStatus\": \"OFF\",\n" +
                                "  \"name\": \"Test Heater\",\n" +
                                "  \"power\": 500,\n" +
                                "  \"roomId\": -10\n" +
                                "}").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Heater"))
                .andExpect(jsonPath("$.heaterStatus").value("OFF"))
                .andExpect(jsonPath("$.power").value(500L))
                .andExpect(jsonPath("$.roomId").value(-10L));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadAHeaterAndReturnNull() throws Exception {
        mockMvc.perform(get("/api/heaters/999").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadAHeaterAndReturnValue() throws Exception {
        mockMvc.perform(get("/api/heaters/-9").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-9L))
                .andExpect(jsonPath("$.name").value("Heater2"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadAllHeaters() throws Exception {
        mockMvc.perform(get("/api/heaters").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldSwitchHeaterStatus() throws Exception {
        Optional<Heater> optHeater = heaterDao.findById(-9L);
        Heater heater = optHeater.get();
        mockMvc.perform(put("/api/heaters/-9/switch").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-9L))
                .andExpect(jsonPath("$.heaterStatus").value(heater.getHeaterStatus() == HeaterStatus.ON ? "OFF" : "ON"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldDeleteHeater() throws Exception {
        mockMvc.perform(delete("/api/heaters/-10").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}