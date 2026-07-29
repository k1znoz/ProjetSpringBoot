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

import fr.cda.ecole.entity.Responsable;
import fr.cda.ecole.service.ResponsableService;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableController {

    private final ResponsableService responsableService;

    public ResponsableController(ResponsableService responsableService) {
        this.responsableService = responsableService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Responsable>> findAll() {
        return ResponseEntity.ok(responsableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsable> findById(@PathVariable Long id) {
        return responsableService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Responsable> save(@RequestBody Responsable responsable) {
        return new ResponseEntity<>(responsableService.save(responsable), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsable> update(@PathVariable Long id, @RequestBody Responsable responsable) {
        if (responsableService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(responsableService.save(responsable));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        responsableService.findById(id).ifPresent(responsable -> responsableService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
