package org.sam.mines.address.service;

import org.sam.mines.address.model.AddressEntity;

import java.util.Optional;
import java.util.UUID;

public interface AddressService {

    Optional<AddressEntity> get(UUID uuid);
}
