package com.emse.spring.faircorp.api;


import com.emse.spring.faircorp.dao.RoomDao;
import com.emse.spring.faircorp.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private RoomDao roomDao;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldCreateRoom() throws Exception {
        mockMvc.perform(post("/api/rooms").contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "  \"buildingId\": -10,\n" +
                                "  \"currentTemperature\": 20,\n" +
                                "  \"floor\": 2,\n" +
                                "  \"name\": \"Test Room\",\n" +
                                "  \"targetTemperature\": 20\n" +
                                "}").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.buildingId").value(-10L))
                .andExpect(jsonPath("$.currentTemperature").value(20))
                .andExpect(jsonPath("$.floor").value(2))
                .andExpect(jsonPath("$.name").value("Test Room"))
                .andExpect(jsonPath("$.targetTemperature").value(20));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadARoomAndReturnNull() throws Exception {
        mockMvc.perform(get("/api/rooms/999").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadARoomAndReturnValue() throws Exception {
        mockMvc.perform(get("/api/rooms/-9").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-9L))
                .andExpect(jsonPath("$.name").value("Room2"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadAllRooms() throws Exception {
        mockMvc.perform(get("/api/rooms").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldSwitchHeatersInRoom() throws Exception {
        Room room = roomDao.findById(-10L).orElseThrow(IllegalArgumentException::new);
        Set<Heater> heatersSet = room.getHeaters();
        Heater[] heaters = heatersSet.toArray(new Heater[heatersSet.size()]);
        mockMvc.perform(put("/api/rooms/-10/switch-heater").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].heaterStatus").value(heaters[0].getHeaterStatus() == HeaterStatus.ON ? "OFF" : "ON"))
                .andExpect(jsonPath("[1].heaterStatus").value(heaters[1].getHeaterStatus() == HeaterStatus.ON ? "OFF" : "ON"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldSwitchWindowsInRoom() throws Exception {
        Room room = roomDao.findById(-10L).orElseThrow(IllegalArgumentException::new);
        Set<Window> windowsSet = room.getWindows();
        Window[] windows = windowsSet.toArray(new Window[windowsSet.size()]);
        mockMvc.perform(put("/api/rooms/-10/switch-window").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].windowStatus").value(windows[0].getWindowStatus() == WindowStatus.OPEN ? "CLOSED" : "OPEN"))
                .andExpect(jsonPath("[1].windowStatus").value(windows[1].getWindowStatus() == WindowStatus.OPEN ? "CLOSED" : "OPEN"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldDeleteRoom() throws Exception {
        mockMvc.perform(delete("/api/rooms/-8").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}