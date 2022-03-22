package org.sam.mines.address.service;

import org.sam.mines.address.model.TownEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TownService {

    List<TownEntity> getAll();

    Optional<TownEntity> get(UUID id);

    TownEntity save(TownEntity town);

    void delete(UUID id);

    List<Integer> findPostCodesForDepartment(int department);
}
