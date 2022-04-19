package org.sam.mines.address.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.sam.mines.address.model.CondamnationEntity;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.persistence.CondamnationRepository;
import org.sam.mines.address.persistence.TownRepository;
import org.sam.mines.address.service.CondamnationService;
import org.sam.mines.address.service.TownService;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CondamnationServiceImplTest {

    @Mock
    private CondamnationRepository condamnationRepository;

    @InjectMocks
    private CondamnationService condamnationService = new CondamnationServiceImpl(condamnationRepository);

    @Test
    public void shouldFindGetAll(){
        when(condamnationRepository.findAll()).thenReturn(List.of(
                CondamnationEntity.CondamnationBuilder.aCondamnation().withCondamnationDurationDay(100).withId(UUID.randomUUID()).build(),
                CondamnationEntity.CondamnationBuilder.aCondamnation().withCondamnationDurationDay(200).withId(UUID.randomUUID()).build()
        ));

        // WHEN
        List<CondamnationEntity> all = condamnationService.getAll();

        // THEN
        assertEquals(2, all.size());
    }

    @Test
    void shouldGetById() {
        // GIVEN
        UUID id = UUID.randomUUID();
        CondamnationEntity value = new CondamnationEntity();
        value.setComdamnationDurationDay(150);
        when(condamnationRepository.findById(id)).thenReturn(Optional.of(value));

        // When
        Optional<CondamnationEntity> condamnation = condamnationService.get(id);

        // THEN
        /*assertTrue(condamnation.isPresent());

        CondamnationEntity condamnation1 = condamnation.get();
        assertEquals(150, condamnation1.getComdamnationDurationDay());

        assertFalse(condamnationService.get(UUID.randomUUID()).isPresent());*/
    }
}
