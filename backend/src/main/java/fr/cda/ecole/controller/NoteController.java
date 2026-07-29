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

import fr.cda.ecole.entity.Note;
import fr.cda.ecole.service.NoteService;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Note>> findAll() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> findById(@PathVariable Long id) {
        return noteService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    public ResponseEntity<Note> save(@RequestBody Note note) {
        return new ResponseEntity<>(noteService.save(note), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> update(@PathVariable Long id, @RequestBody Note note) {
        if (noteService.findById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(noteService.save(note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        noteService.findById(id).ifPresent(note -> noteService.deleteById(id));
        return ResponseEntity.noContent().build();
    }
}
