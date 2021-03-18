package com.example.autogarage.controller;

import com.example.autogarage.Service.ComponentService;
import com.example.autogarage.model.Action;
import com.example.autogarage.model.Component;
import com.example.autogarage.repository.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;


@RestController
@RequestMapping(value="/component")
// Enkel monteur, back en kassa kunnen gegevens; inzien, maken, updaten en verwijderen
@PreAuthorize("hasAnyRole('MONTEUR','BACK','KASSA')")

public class ComponentController {
    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ComponentService componentService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Component>> getComponent() {
        return ResponseEntity.ok().body(componentService.getComponents());
    }

    @PostMapping(value = "")
    public ResponseEntity<URI> createComponent(@RequestBody Component component) {
        long newId = componentService.createComponent(component);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateComponent(@PathVariable("id") long id, @RequestBody Component component) {
        componentService.updateComponent(id, component);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateComponentPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields, Map<Double, Double>fields2) {
        componentService.partialUpdateComponent(id,fields, fields2);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteComponent(@PathVariable("id") long id) {
        componentService.deleteComponent(id);
        return ResponseEntity.noContent().build();
    }
}
