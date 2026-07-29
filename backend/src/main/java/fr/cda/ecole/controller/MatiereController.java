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

import fr.cda.ecole.entity.Matiere;
import fr.cda.ecole.service.MatiereService;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    private final MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Matiere>> findAll() {
        return ResponseEntity.ok(matiereService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> findById(@PathVariable Long id) {
        return matiereService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Matiere> save(@RequestBody Matiere matiere) {
        return new ResponseEntity<>(matiereService.save(matiere), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> update(@PathVariable Long id, @RequestBody Matiere matiere) {
        if (matiereService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(matiereService.save(matiere));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        matiereService.findById(id).ifPresent(matiere -> matiereService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
