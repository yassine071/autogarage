package com.example.autogarage.controller;

import com.example.autogarage.Service.ReparationService;
import com.example.autogarage.model.Action;
import com.example.autogarage.model.Reparation;
import com.example.autogarage.repository.ReparationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value="/reparation")

// Enkel monteur en admin kunnen gegevens; inzien, maken, updaten en verwijderen
@PreAuthorize("hasAnyRole('ADMIN','MONTEUR')")

public class ReparationController {
    @Autowired
    private ReparationRepository reparationRepository;

    @Autowired
    private ReparationService reparationService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Reparation>> getReparation() {
        return ResponseEntity.ok().body(reparationService.getReparation());
    }
    @PostMapping(value = "")
    public ResponseEntity<URI> createReparation(@RequestBody Reparation reparation) {
        long newId = reparationService.createReparation(reparation);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateReparation(@PathVariable("id") long id, @RequestBody Reparation reparation) {
        reparationService.updateReparation(id, reparation);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateReparationPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields) {
        reparationService.partialUpdateReparation(id,fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteReparation(@PathVariable("id") long id) {
        reparationService.deleteReparation(id);
        return ResponseEntity.noContent().build();
    }
}
