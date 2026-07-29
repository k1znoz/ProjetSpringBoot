package fr.cda.ecole.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.cda.ecole.entity.Inscription;
import fr.cda.ecole.service.InscriptionService;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {

    private final InscriptionService inscriptionService;

    public InscriptionController(InscriptionService inscriptionService) {
        this.inscriptionService = inscriptionService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Inscription>> findAll() {
        return ResponseEntity.ok(inscriptionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscription> findById(@PathVariable Long id) {
        return inscriptionService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Inscription> save(@RequestBody Inscription inscription) {
        return new ResponseEntity<>(inscriptionService.save(inscription), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Inscription> update(@PathVariable Long id, @RequestBody Inscription inscription) {
        if (inscriptionService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(inscriptionService.save(inscription));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        inscriptionService.findById(id).ifPresent(inscription -> inscriptionService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
