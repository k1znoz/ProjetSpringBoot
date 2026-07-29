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

import fr.cda.ecole.entity.Enseignant;
import fr.cda.ecole.service.EnseignantService;

@RestController
@RequestMapping("/api/enseignants")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Enseignant>> findAll() {
        return ResponseEntity.ok(enseignantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> findById(@PathVariable Long id) {
        return enseignantService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Enseignant> save(@RequestBody Enseignant enseignant) {
        return new ResponseEntity<>(enseignantService.save(enseignant), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> update(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        if (enseignantService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(enseignantService.save(enseignant));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        enseignantService.findById(id).ifPresent(enseignant -> enseignantService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
