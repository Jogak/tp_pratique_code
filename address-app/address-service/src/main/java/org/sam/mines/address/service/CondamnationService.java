package org.sam.mines.address.service;

import org.sam.mines.address.model.CondamnationEntity;
import org.sam.mines.address.model.TownEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CondamnationService {
    List<CondamnationEntity> getAll();

    Optional<CondamnationEntity> get(UUID id);

    CondamnationEntity save(CondamnationEntity condamnation);

    void delete(UUID id);


}
