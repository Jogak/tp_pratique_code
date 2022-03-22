package org.sam.mines.address.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.persistence.TownRepository;
import org.sam.mines.address.service.TownService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TownServiceImplTest {

    @Mock
    private TownRepository townRepository;

    @InjectMocks
    private TownService townService = new TownServiceImpl(townRepository);

    @Test
    void shouldNotSaveWhenNameIsBlank() {
        // GIVEN
        // WHEN
        TownEntity town = new TownEntity();
        // THEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(town));
    }

    @Test
    void shouldNotSaveExistingTown() {
        // GIVEN
        TownEntity townEntity = TownEntity.TownBuilder.aTown().withId(UUID.randomUUID()).build();
        when(townRepository.findAllByName(anyString())).thenReturn(List.of(townEntity));

        // WHEN
        TownEntity newTown = TownEntity.TownBuilder.aTown().withName("Montpellier").build();
        // THEN
        assertThrows(IllegalArgumentException.class, () -> townService.save(newTown));
    }

    @Test
    void shouldSaveAValidTown() {
        // GIVEN
        TownEntity town = TownEntity.TownBuilder.aTown().withName("a town").build();

        // WHEN
        UUID generatedId = UUID.randomUUID();
        when(townRepository.save(any(TownEntity.class)))
                .thenReturn(TownEntity.TownBuilder.aTown().withId(generatedId).build());

        // THEN
        TownEntity persisted = townService.save(town);
        assertEquals(persisted.getId(), generatedId);
    }

    @Test
    void shouldGetAll() {
        // GIVEN
        when(townRepository.findAll()).thenReturn(List.of(
                TownEntity.TownBuilder.aTown().withName("town 1").withId(UUID.randomUUID()).build(),
                TownEntity.TownBuilder.aTown().withName("town 2").withId(UUID.randomUUID()).build()
        ));

        // WHEN
        List<TownEntity> all = townService.getAll();

        // THEN
        assertEquals(2, all.size());
    }

    @Test
    void shouldGetById() {
        // GIVEN
        UUID id = UUID.randomUUID();
        TownEntity value = new TownEntity();
        value.setName("aTown");
        when(townRepository.findById(id)).thenReturn(Optional.of(value));

        // When
        Optional<TownEntity> town = townService.get(id);

        // THEN
        assertTrue(town.isPresent());

        TownEntity town1 = town.get();
        assertEquals("aTown", town1.getName());

        assertFalse(townService.get(UUID.randomUUID()).isPresent());
    }

    @Test
    void shouldNotGetById() {
        // GIVEN
        UUID id = UUID.randomUUID();
        when(townRepository.findById(id)).thenReturn(Optional.empty());

        // When
        Optional<TownEntity> town = townService.get(id);

        // THEN
        assertFalse(town.isPresent());


    }
}