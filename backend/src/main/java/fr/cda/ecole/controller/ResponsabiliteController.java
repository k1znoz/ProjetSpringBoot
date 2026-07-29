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

import fr.cda.ecole.entity.Responsabilite;
import fr.cda.ecole.entity.ResponsabiliteId;
import fr.cda.ecole.service.ResponsabiliteService;

@RestController
@RequestMapping("/api/responsabilites")
public class ResponsabiliteController {

    private final ResponsabiliteService responsabiliteService;

    public ResponsabiliteController(ResponsabiliteService responsabiliteService) {
        this.responsabiliteService = responsabiliteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Responsabilite>> findAll() {
        return ResponseEntity.ok(responsabiliteService.findAll());
    }

    @GetMapping("/{idResponsable}/{idEleve}")
    public ResponseEntity<Responsabilite> findById(
            @PathVariable Long idResponsable,
            @PathVariable Long idEleve
    ) {
        ResponsabiliteId id = new ResponsabiliteId(idResponsable, idEleve);
        return responsabiliteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Responsabilite> save(@RequestBody Responsabilite responsabilite) {
        return new ResponseEntity<>(responsabiliteService.save(responsabilite), HttpStatus.CREATED);
    }

    @PutMapping("/{idResponsable}/{idEleve}")
    public ResponseEntity<Responsabilite> update(
            @PathVariable Long idResponsable,
            @PathVariable Long idEleve,
            @RequestBody Responsabilite responsabilite
    ) {
        ResponsabiliteId id = new ResponsabiliteId(idResponsable, idEleve);
        if (responsabiliteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        responsabilite.setId(id);
        return ResponseEntity.ok(responsabiliteService.save(responsabilite));
    }

    @DeleteMapping("/{idResponsable}/{idEleve}")
    public ResponseEntity<Void> deleteById(
            @PathVariable Long idResponsable,
            @PathVariable Long idEleve
    ) {
        ResponsabiliteId id = new ResponsabiliteId(idResponsable, idEleve);
        responsabiliteService.findById(id).ifPresent(responsabilite -> responsabiliteService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
