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

import fr.cda.ecole.entity.Eleve;
import fr.cda.ecole.service.EleveService;

@RestController
@RequestMapping("/api/eleves")
public class EleveController {

    private final EleveService eleveService;

    public EleveController(EleveService eleveService) {
        this.eleveService = eleveService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Eleve>> findAll() {
        return ResponseEntity.ok(eleveService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Eleve> findById(@PathVariable Long id) {
        return eleveService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Eleve> save(@RequestBody Eleve eleve) {
        return new ResponseEntity<>(eleveService.save(eleve), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Eleve> update(@PathVariable Long id, @RequestBody Eleve eleve) {
        if (eleveService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(eleveService.save(eleve));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        eleveService.findById(id).ifPresent(eleve -> eleveService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
