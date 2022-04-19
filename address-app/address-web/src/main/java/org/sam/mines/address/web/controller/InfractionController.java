package org.sam.mines.address.web.controller;

import org.sam.mines.address.api.controller.InfractionApi;
import org.sam.mines.address.api.model.Infraction;
import org.sam.mines.address.api.model.Town;
import org.sam.mines.address.model.InfractionEntity;
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

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class InfractionController implements InfractionApi {

    private InfractionService infractionService;

    @Autowired
    public InfractionController(InfractionService infractionService) {
        this.infractionService = infractionService;
    }

    @Override
    public ResponseEntity<Infraction> createinfractionlist(Infraction infraction) {
        InfractionEntity infractionEntity = infractionService.save(this.map(infraction));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(infraction.getId()).toUri();

        return ResponseEntity.created(uri).body(map(infractionEntity));
    }

    @Override
    public ResponseEntity<String> deleteinfractionid(String id) {
        infractionService.delete(UUID.fromString(id));

        return ResponseEntity.ok(id);
    }

    @Override
    public ResponseEntity<Infraction> getinfractionid(String id) {
        try {
            UUID uuid = UUID.fromString(id);

            return infractionService.get(uuid)
                    .map(this::map)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            // Logger
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<List<Infraction>> getinfractionlist() {
        return ResponseEntity.ok(infractionService.getAll().stream().map(this::map).collect(Collectors.toList()));
    }

    @Override
    public ResponseEntity<Infraction> updateinfractionlist(Infraction infraction) {
        InfractionEntity modelInfraction = this.map(infraction);

        if (infractionService.get(modelInfraction.getId()).isPresent()) {
            return ResponseEntity.ok(this.map(infractionService.save(modelInfraction)));
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }



    private InfractionEntity map(Infraction infraction) {
        return InfractionEntity.InfractionBuilder.aInfraction()
                .withId(infraction.getId() == null ? null : UUID.fromString(infraction.getId()))
                .withDescription(infraction.getDescription())
                .withMobile(infraction.getMobile())
                .withPlace(infraction.getPlace())
                .withNumber((infraction.getNumber().toBigInteger().intValue()))
                .build();
    }

    private Infraction map(InfractionEntity infractionEntity) {

        Infraction infraction = new Infraction();
        infraction.setNumber(BigDecimal.valueOf(infractionEntity.getNumber()));
        infraction.setDescription(infractionEntity.getDescription());
        infraction.setPlace(infractionEntity.getPlace());
        infraction.setMobile(infractionEntity.getMobile());
        infraction.setId(String.valueOf(infractionEntity.getId()));
        return infraction;
    }
}
