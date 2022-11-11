package com.emse.spring.faircorp.api;

import com.emse.spring.faircorp.dao.WindowDao;
import com.emse.spring.faircorp.model.Window;
import com.emse.spring.faircorp.model.WindowStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc()
public class WindowControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WindowDao windowDao;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldCreateWindow() throws Exception {
        mockMvc.perform(post("/api/windows").contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "  \"name\": \"Test Window\",\n" +
                                "  \"roomId\": -10,\n" +
                                "  \"windowStatus\": \"CLOSED\"\n" +
                                "}").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Window"))
                .andExpect(jsonPath("$.windowStatus").value("CLOSED"))
                .andExpect(jsonPath("$.roomName").value("Room1"))
                .andExpect(jsonPath("$.roomId").value(-10L));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAWindowAndReturnNull() throws Exception {
        mockMvc.perform(get("/api/windows/999").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAWindowAndReturnValue() throws Exception {
        mockMvc.perform(get("/api/windows/-9").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-9L))
                .andExpect(jsonPath("$.name").value("Window 2"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldLoadAllWindows() throws Exception {
        mockMvc.perform(get("/api/windows").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldSwitchStatus() throws Exception {
        Optional<Window> optWindow = windowDao.findById(-9L);
        Window window = optWindow.get();
        mockMvc.perform(put("/api/windows/-9/switch").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(-9L))
                .andExpect(jsonPath("$.windowStatus").value(window.getWindowStatus() == WindowStatus.OPEN ? "CLOSED" : "OPEN"));
    }

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/api/windows/-10").accept(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }
}