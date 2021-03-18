package com.example.autogarage.controller;

import com.example.autogarage.Service.ExaminationService;
import com.example.autogarage.model.Action;
import com.example.autogarage.model.Examination;
import com.example.autogarage.repository.ExaminationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping(value="/examination")

// Enkel monteur en admin kunnen gegevens; inzien, maken, updaten en verwijderen
@PreAuthorize("hasAnyRole('ADMIN','MONTEUR')")

public class ExaminationController {
    @Autowired
    private ExaminationRepository examinationRepository;

    @Autowired
    private ExaminationService examinationService;

    @GetMapping(value = "")
    public ResponseEntity<Collection<Examination>> getExamination() {
        return ResponseEntity.ok().body(examinationService.getExamination());
    }


    @PostMapping(value = "")
    public ResponseEntity<URI> createExamination(@RequestBody Examination examination) {
        long newId = examinationService.createExamination(examination);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateExamination(@PathVariable("id") long id, @RequestBody Examination examination) {
        examinationService.updateExamination(id, examination);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Object> updateExaminationPartial(@PathVariable("id") long id, @RequestBody Map<String, String> fields) {
        examinationService.partialUpdateExamination(id,fields);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteExamination(@PathVariable("id") long id) {
        examinationService.deleteExamination(id);
        return ResponseEntity.noContent().build();
    }
}
