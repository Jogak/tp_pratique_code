package org.sam.mines.address.service;

import org.sam.mines.address.model.InfractionEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InfractionService {
    List<InfractionEntity> getAll();

    Optional<InfractionEntity> get(UUID id);

    InfractionEntity save(InfractionEntity condamnation);

    void delete(UUID id);

}
