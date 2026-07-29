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

import fr.cda.ecole.entity.Bulletin;
import fr.cda.ecole.service.BulletinService;

@RestController
@RequestMapping("/api/bulletins")
public class BulletinController {

    private final BulletinService bulletinService;

    public BulletinController(BulletinService bulletinService) {
        this.bulletinService = bulletinService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Bulletin>> findAll() {
        return ResponseEntity.ok(bulletinService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bulletin> findById(@PathVariable Long id) {
        return bulletinService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Bulletin> save(@RequestBody Bulletin bulletin) {
        return new ResponseEntity<>(bulletinService.save(bulletin), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bulletin> update(@PathVariable Long id, @RequestBody Bulletin bulletin) {
        if (bulletinService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bulletinService.save(bulletin));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        bulletinService.findById(id).ifPresent(bulletin -> bulletinService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
