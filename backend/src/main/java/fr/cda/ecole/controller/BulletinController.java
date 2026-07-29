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

import fr.cda.ecole.dto.BulletinDto;
import fr.cda.ecole.service.BulletinService;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {

    private final BulletinService bulletinService;

    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @GetMapping("/")
    public ResponseEntity<List<BulletinDto>> findAll() {
        return ResponseEntity.ok(bulletinService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BulletinDto> findById(@PathVariable Long id) {
        return bulletinService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<BulletinDto> save(@Valid @RequestBody BulletinDto bulletinDto) {
        return new ResponseEntity<>(bulletinService.save(bulletinDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BulletinDto> update(@PathVariable Long id, @Valid @RequestBody BulletinDto bulletinDto) {
        if (bulletinService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        bulletinDto.setIdBulletin(id);
        return ResponseEntity.ok(bulletinService.save(bulletinDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bulletinService.findById(id).ifPresent(bulletin -> bulletinService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
