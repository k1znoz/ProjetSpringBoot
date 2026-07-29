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

import fr.cda.ecole.entity.Utilisateur;
import fr.cda.ecole.service.UtilisateurService;

@RestController
@RequestMapping("/api/utilisateurs")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Utilisateur>> findAll() {
        return ResponseEntity.ok(utilisateurService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> findById(@PathVariable Long id) {
        return utilisateurService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Utilisateur> save(@RequestBody Utilisateur utilisateur) {
        return new ResponseEntity<>(utilisateurService.save(utilisateur), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
        if (utilisateurService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(utilisateurService.save(utilisateur));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        utilisateurService.findById(id).ifPresent(utilisateur -> utilisateurService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
