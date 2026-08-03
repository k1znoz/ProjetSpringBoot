package fr.cda.ecole.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cda.ecole.dto.NoteDto;
import fr.cda.ecole.entity.Eleve;
import fr.cda.ecole.entity.Matiere;
import fr.cda.ecole.entity.Note;
import fr.cda.ecole.repository.NoteRepository;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @Mock
    private NoteRepository noteRepository;

    @InjectMocks
    private NoteService noteService;

    @Test
    void findAll_shouldReturnMappedDtos() {
        Note n1 = buildNote(1L, new BigDecimal("15.50"), 1L, 1L);
        Note n2 = buildNote(2L, new BigDecimal("12.00"), 1L, 1L);
        when(noteRepository.findAll()).thenReturn(List.of(n1, n2));

        List<NoteDto> result = noteService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getIdNote()).isEqualTo(1L);
        assertThat(result.get(0).getIdEleve()).isEqualTo(1L);
        assertThat(result.get(1).getValeur()).isEqualByComparingTo("12.00");
    }

    @Test
    void findById_shouldReturnDtoWhenPresent() {
        Note entity = buildNote(3L, new BigDecimal("14.00"), 1L, 1L);
        when(noteRepository.findById(3L)).thenReturn(Optional.of(entity));

        Optional<NoteDto> result = noteService.findById(3L);

        assertThat(result).isPresent();
        assertThat(result.get().getTypeEvaluation()).isEqualTo("Controle");
        assertThat(result.get().getIdMatiere()).isEqualTo(1L);
    }

    @Test
    void save_shouldPersistAndReturnMappedDto() {
        NoteDto input = new NoteDto();
        input.setValeur(new BigDecimal("13.50"));
        input.setDateNote(LocalDate.of(2025, 10, 13));
        input.setCommentaire("Bon");
        input.setTypeEvaluation("Controle");
        input.setIdEleve(1L);
        input.setIdMatiere(1L);

        when(noteRepository.save(any(Note.class))).thenAnswer(invocation -> {
            Note saved = invocation.getArgument(0);
            saved.setIdNote(7L);
            return saved;
        });

        NoteDto result = noteService.save(input);

        assertThat(result.getIdNote()).isEqualTo(7L);
        assertThat(result.getValeur()).isEqualByComparingTo("13.50");
        assertThat(result.getIdEleve()).isEqualTo(1L);
        assertThat(result.getIdMatiere()).isEqualTo(1L);
        verify(noteRepository).save(any(Note.class));
    }

    @Test
    void deleteById_shouldDelegateToRepository() {
        noteService.deleteById(9L);

        verify(noteRepository).deleteById(9L);
    }

    private Note buildNote(Long id, BigDecimal valeur, Long idEleve, Long idMatiere) {
        Eleve eleve = new Eleve();
        eleve.setIdEleve(idEleve);

        Matiere matiere = new Matiere();
        matiere.setIdMatiere(idMatiere);

        Note note = new Note();
        note.setIdNote(id);
        note.setValeur(valeur);
        note.setDateNote(LocalDate.of(2025, 10, 13));
        note.setCommentaire("Bon");
        note.setTypeEvaluation("Controle");
        note.setEleve(eleve);
        note.setMatiere(matiere);
        return note;
    }
}
