package org.sam.mines.address.web.controller;

import org.sam.mines.address.api.controller.InfractionApi;
import org.sam.mines.address.api.model.Infraction;
import org.sam.mines.address.api.model.Town;
import org.sam.mines.address.model.TownEntity;
import org.sam.mines.address.service.InfractionService;
import org.sam.mines.address.service.TownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class InfractionController extends InfractionApi {

    private InfractionService infractionService;

    @Autowired
    public InfractionController(InfractionService infractionService) {
        this.infractionService = infractionService;
    }

    @Override
    public ResponseEntity<Infraction> createinfractionlist(Infraction infraction) {
        return InfractionApi.super.createinfractionlist(infraction);
    }

    @Override
    public ResponseEntity<String> deleteinfractionid(String id) {
        return InfractionApi.super.deleteinfractionid(id);
    }

    @Override
    public ResponseEntity<Infraction> getinfractionid(String id) {
        return InfractionApi.super.getinfractionid(id);
    }

    @Override
    public ResponseEntity<List<Infraction>> getinfractionlist() {
        return InfractionApi.super.getinfractionlist();
    }

    @Override
    public ResponseEntity<Infraction> updateinfractionlist(Infraction infraction) {
        return InfractionApi.super.updateinfractionlist(infraction);
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
