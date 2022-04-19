package org.sam.mines.address.web;

import org.junit.jupiter.api.Test;
import org.sam.mines.address.model.InfractionEntity;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.service.InfractionService;
import org.sam.mines.address.service.TownService;
import org.sam.mines.address.web.config.WebTestConfig;
import org.sam.mines.address.web.controller.InfractionController;
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

@WebMvcTest(InfractionController.class)
@Import(WebTestConfig.class)
public class InfractionControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private InfractionService infractionService;

    @Autowired
    public InfractionControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldGetInfraction() throws Exception {
        //Given

        UUID uuid = UUID.randomUUID();

        // When
        when(infractionService.get(uuid)).thenReturn(Optional.of(
                InfractionEntity.InfractionBuilder.aInfraction().withId(uuid).withDescription("Julia killed the respect").build()
        ));

        // Then

        mockMvc.perform(MockMvcRequestBuilders.get("/infraction/%s".formatted(uuid.toString()))
                        .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Julia killed the respect"));
    }
}
