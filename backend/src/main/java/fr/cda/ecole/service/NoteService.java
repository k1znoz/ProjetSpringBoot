package fr.cda.ecole.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import fr.cda.ecole.dto.NoteDto;
import fr.cda.ecole.mapper.NoteMapper;
import fr.cda.ecole.repository.NoteRepository;

@Service
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<NoteDto> findAll() {
        return noteRepository.findAll().stream()
                .map(NoteMapper::toDto)
                .toList();
    }

    public Optional<NoteDto> findById(Long id) {
        return noteRepository.findById(id)
                .map(NoteMapper::toDto);
    }

    public NoteDto save(NoteDto noteDto) {
        return NoteMapper.toDto(
                noteRepository.save(NoteMapper.toEntity(noteDto))
        );
    }

    public void deleteById(Long id) {
        noteRepository.deleteById(id);
    }
}
