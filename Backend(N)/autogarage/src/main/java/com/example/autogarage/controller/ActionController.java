package com.example.autogarage.controller;

import com.example.autogarage.Service.ActionService;
import com.example.autogarage.model.Action;
import com.example.autogarage.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;


@RequestMapping(value="/action")
@RestController

// Enkel monteur kan de gegevens; inzien, maken, updaten en verwijderen
@PreAuthorize("hasAnyRole('MONTEUR')")
public class ActionController {

    @Autowired
    private ActionService actionService;


    @GetMapping(value = "")
    public ResponseEntity<Collection<Action>> getAction() {
        return ResponseEntity.ok().body(actionService.getActions());
    }


    @PostMapping(value = "")

    public ResponseEntity<URI> createAction(@RequestBody Action action) {
        long newId = actionService.createAction(action);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")

    public ResponseEntity<Object> updateAction(@PathVariable("id") long id, @RequestBody Action action) {
        actionService.updateAction(id, action);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")

    public ResponseEntity<Object> updateActionPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields, Map<Double, Double>fields2) {
        actionService.partialUpdateAction(id,fields, fields2);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")

    public ResponseEntity<Object> deleteAction(@PathVariable("id") long id) {
        actionService.deleteAction(id);
        return ResponseEntity.noContent().build();
    }

}


