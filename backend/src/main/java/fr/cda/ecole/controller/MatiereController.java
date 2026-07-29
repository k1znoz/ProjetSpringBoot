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

import fr.cda.ecole.dto.MatiereDto;
import fr.cda.ecole.service.MatiereService;

@RestController
@RequestMapping("/api/matieres")
public class MatiereController {

    private final MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping("/")
    public ResponseEntity<List<MatiereDto>> findAll() {
        return ResponseEntity.ok(matiereService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatiereDto> findById(@PathVariable Long id) {
        return matiereService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<MatiereDto> save(@RequestBody MatiereDto matiereDto) {
        return new ResponseEntity<>(matiereService.save(matiereDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MatiereDto> update(@PathVariable Long id, @RequestBody MatiereDto matiereDto) {
        if (matiereService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        matiereDto.setIdMatiere(id);
        return ResponseEntity.ok(matiereService.save(matiereDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        matiereService.findById(id).ifPresent(matiere -> matiereService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
