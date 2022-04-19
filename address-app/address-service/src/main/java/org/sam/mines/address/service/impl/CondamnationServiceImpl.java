package org.sam.mines.address.service.impl;

import org.sam.mines.address.model.CondamnationEntity;
import org.sam.mines.address.persistence.CondamnationRepository;
import org.sam.mines.address.service.CondamnationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class CondamnationServiceImpl implements CondamnationService {
    private CondamnationRepository condamnationRepository;

    @Autowired
    public CondamnationServiceImpl(CondamnationRepository condamnationRepository){this.condamnationRepository = condamnationRepository;}
    @Override
    public List<CondamnationEntity> getAll() {
        return this.condamnationRepository.findAll();
    }

    @Override
    public Optional<CondamnationEntity> get(UUID id) {
        return Optional.of(Optional.of(this.condamnationRepository.getById(id)).orElse(null));
    }

    @Override
    public CondamnationEntity save(CondamnationEntity condamnation) {
        Assert.hasText(String.valueOf(condamnation.getComdamnationDurationDay()), "Condamnation duration is required");

        // This is a validation example: the rule should probably be relaxed
        // as multiple towns can have the same name


        return condamnationRepository.save(condamnation);
    }

    @Override
    public void delete(UUID id) {
        condamnationRepository.deleteById(id);
    }
}
