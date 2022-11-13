package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.BuildingDao;
import com.emse.spring.faircorp.model.Building;
import com.emse.spring.faircorp.model.BuildingStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
public class BuildingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BuildingDao buildingDao;

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldCreateBuilding() throws Exception {
        mockMvc.perform(post("/api/building").contentType(APPLICATION_JSON)
                        .content("{\n" +
                                " \"buildingStatus\": \"LOCKED\"\n," +
                                "  \"amountOfFloors\": 5,\n" +
                                "  \"name\": \"Test Building\"\n" +
                                "}").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.amountOfFloors").value(5))
                .andExpect(jsonPath("$.name").value("Test Building"))
                .andExpect(jsonPath("$.buildingStatus").value("LOCKED"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadABuildingAndReturnNull() throws Exception {
        mockMvc.perform(get("/api/building/999").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadABuildingAndReturnValue() throws Exception {
        mockMvc.perform(get("/api/building/-10").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-10L))
                .andExpect(jsonPath("$.name").value("Espace Fauriel"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldLoadAllBuildings() throws Exception {
        mockMvc.perform(get("/api/building").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldSwitchBuildingStatus() throws Exception {
        Optional<Building> optBuilding = buildingDao.findById(-10L);
        Building building = optBuilding.get();
        mockMvc.perform(put("/api/building/-10/switch").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-10L))
                .andExpect(jsonPath("$.buildingStatus").value(building.getBuildingStatus() == BuildingStatus.LOCKED ? "UNLOCKED" : "LOCKED"));
    }

    @Test
    @WithMockUser(username = "user", roles = "USER")
    void shouldDeleteBuilding() throws Exception {
        mockMvc.perform(delete("/api/building/-9").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}
