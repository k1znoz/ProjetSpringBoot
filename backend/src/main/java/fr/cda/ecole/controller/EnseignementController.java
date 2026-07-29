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

import fr.cda.ecole.dto.EnseignementDto;
import fr.cda.ecole.service.EnseignementService;

@RestController
@RequestMapping("/api/enseignements")
public class EnseignementController {

    private final EnseignementService enseignementService;

    public EnseignementController(EnseignementService enseignementService) {
        this.enseignementService = enseignementService;
    }

    @GetMapping("/")
    public ResponseEntity<List<EnseignementDto>> findAll() {
        return ResponseEntity.ok(enseignementService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnseignementDto> findById(@PathVariable Long id) {
        return enseignementService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<EnseignementDto> save(@RequestBody EnseignementDto enseignementDto) {
        return new ResponseEntity<>(enseignementService.save(enseignementDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnseignementDto> update(@PathVariable Long id, @RequestBody EnseignementDto enseignementDto) {
        if (enseignementService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        enseignementDto.setIdEnseignement(id);
        return ResponseEntity.ok(enseignementService.save(enseignementDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        enseignementService.findById(id).ifPresent(enseignement -> enseignementService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
