package org.sam.mines.address.service.impl;

import org.sam.mines.address.service.TownService;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.persistence.TownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TownServiceImpl implements TownService {

    private TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public List<TownEntity> getAll() {
        return townRepository.findAll();
    }

    @Override
    @Transactional
    public Optional<TownEntity> get(UUID id) {
        return townRepository.findById(id);
    }

    @Override
    public TownEntity save(TownEntity town) {
        Assert.hasText(town.getName(), "Name is required");

        // This is a validation example: the rule should probably be relaxed
        // as multiple towns can have the same name
        if (!townRepository.findAllByName(town.getName()).isEmpty()) {
            throw new IllegalArgumentException("Town name already exists");
        }

        return townRepository.save(town);
    }

    @Override
    public void delete(UUID id) {
        townRepository.deleteById(id);
    }

    @Override
    public List<Integer> findPostCodesForDepartment(int department) {
        return townRepository.findAll().stream()
                .map(TownEntity::getPostCode)
                .filter(postCode -> postCode % 1000 == department).toList();
    }
}
