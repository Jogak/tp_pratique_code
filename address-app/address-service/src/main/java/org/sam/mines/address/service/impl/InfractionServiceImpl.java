package org.sam.mines.address.service.impl;

import org.sam.mines.address.model.InfractionEntity;
import org.sam.mines.address.persistence.CondamnationRepository;
import org.sam.mines.address.persistence.InfractionRepository;
import org.sam.mines.address.service.InfractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class InfractionServiceImpl implements InfractionService {

    private InfractionRepository infractionRepository;

    @Autowired
    public InfractionServiceImpl(InfractionRepository infractionRepository){this.infractionRepository = infractionRepository;}

    @Override
    public List<InfractionEntity> getAll() {
        return this.infractionRepository.findAll();
    }

    @Override
    public Optional<InfractionEntity> get(UUID id) {
        return Optional.of(Optional.of(this.infractionRepository.getById(id)).orElse(null));
    }

    @Override
    public InfractionEntity save(InfractionEntity infraction) {
        Assert.hasText(String.valueOf(infraction.getDescription()), "Condamnation duration is required");

        // This is a validation example: the rule should probably be relaxed
        // as multiple towns can have the same name


        return infractionRepository.save(infraction);
    }

    @Override
    public void delete(UUID id) {

    }
}
