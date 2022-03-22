package org.sam.mines.address.web.controller;

import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.service.TownService;
import org.sam.mines.address.api.controller.TownApi;
import org.sam.mines.address.api.model.Town;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class TownController implements TownApi {
    private TownService townService;

    @Autowired
    public TownController(TownService townService) {
        this.townService = townService;
    }

    @Override
    public ResponseEntity<Town> create(Town town) {
        TownEntity saved = townService.save(this.map(town));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(saved.getId()).toUri();

        return ResponseEntity.created(uri).body(map(saved));
    }

    @Override
    public ResponseEntity<String> delete(String id) {
        townService.delete(UUID.fromString(id));

        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Town> get(String id) {
        try {
            UUID uuid = UUID.fromString(id);

            return townService.get(uuid)
                    .map(this::map)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            // Logger
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Town>> list() {
        return ResponseEntity.ok(townService.getAll().stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Town> update(Town town) {
        TownEntity modelTown = this.map(town);

        if (townService.get(modelTown.getId()).isPresent()) {
            return ResponseEntity.ok(this.map(townService.save(modelTown)));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    private TownEntity map(Town town) {
        return TownEntity.TownBuilder.aTown()
                .withId(town.getId() == null ? null : UUID.fromString(town.getId()))
                .withName(town.getName())
                .withPostCode(Integer.parseInt(town.getPostCode()))
                .build();
    }

    private Town map(TownEntity town) {

        Town apiTown = new Town();
        apiTown.setId(town.getId().toString());
        apiTown.setName(town.getName());
        apiTown.setPostCode(String.valueOf(town.getPostCode()));

        return apiTown;
    }
}
