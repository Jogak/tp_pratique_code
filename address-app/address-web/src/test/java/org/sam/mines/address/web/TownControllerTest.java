package org.sam.mines.address.web;

import org.junit.jupiter.api.Test;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.service.TownService;
import org.sam.mines.address.web.config.WebTestConfig;
import org.sam.mines.address.web.controller.TownController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(TownController.class)
@Import(WebTestConfig.class)
public class TownControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private TownService townService;

    @Autowired
    public TownControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldGetATown() throws Exception {
        // Given
        UUID uuid = UUID.randomUUID();

        // When
        when(townService.get(uuid)).thenReturn(Optional.of(
                TownEntity.TownBuilder.aTown().withId(uuid).withName("some town").build()
        ));

        // Then
        mockMvc.perform(MockMvcRequestBuilders.get("/town/%s".formatted(uuid.toString()))
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("some town"));

    }

    @Test
    void shouldNotGetInstanceData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/town/%s".formatted(UUID.randomUUID().toString()))
                .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
