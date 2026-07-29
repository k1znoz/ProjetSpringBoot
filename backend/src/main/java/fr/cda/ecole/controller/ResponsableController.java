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

import fr.cda.ecole.dto.ResponsableDto;
import fr.cda.ecole.service.ResponsableService;

@RestController
@RequestMapping("/api/responsables")
public class ResponsableController {

    private final ResponsableService responsableService;

    public ResponsableController(ResponsableService responsableService) {
        this.responsableService = responsableService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ResponsableDto>> findAll() {
        return ResponseEntity.ok(responsableService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponsableDto> findById(@PathVariable Long id) {
        return responsableService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<ResponsableDto> save(@RequestBody ResponsableDto responsableDto) {
        return new ResponseEntity<>(responsableService.save(responsableDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponsableDto> update(@PathVariable Long id, @RequestBody ResponsableDto responsableDto) {
        if (responsableService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        responsableDto.setIdResponsable(id);
        return ResponseEntity.ok(responsableService.save(responsableDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        responsableService.findById(id).ifPresent(responsable -> responsableService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
