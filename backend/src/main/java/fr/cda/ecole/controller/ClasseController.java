package fr.cda.ecole.controller;

import java.util.List;

import jakarta.validation.Valid;
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

import fr.cda.ecole.dto.ClasseDto;
import fr.cda.ecole.service.ClasseService;

@RestController
@RequestMapping("/api/classes")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping("/")
    public ResponseEntity<List<ClasseDto>> findAll() {
        return ResponseEntity.ok(classeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClasseDto> findById(@PathVariable Long id) {
        return classeService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<ClasseDto> save(@Valid @RequestBody ClasseDto classeDto) {
        return new ResponseEntity<>(classeService.save(classeDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClasseDto> update(@PathVariable Long id, @Valid @RequestBody ClasseDto classeDto) {
        if (classeService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        classeDto.setIdClasse(id);
        return ResponseEntity.ok(classeService.save(classeDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        classeService.findById(id).ifPresent(classe -> classeService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
