package fr.cda.ecole.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import fr.cda.ecole.dto.EleveDto;
import fr.cda.ecole.entity.Eleve;
import fr.cda.ecole.repository.EleveRepository;

@ExtendWith(MockitoExtension.class)
class EleveServiceTest {

    @Mock
    private EleveRepository eleveRepository;

    @InjectMocks
    private EleveService eleveService;

    @Test
    void findAll_shouldReturnMappedDtos() {
        Eleve e1 = buildEleve(1L, "Dupont", "Leo", "leo@ecole.fr");
        Eleve e2 = buildEleve(2L, "Martin", "Lina", "lina@ecole.fr");
        when(eleveRepository.findAll()).thenReturn(List.of(e1, e2));

        List<EleveDto> result = eleveService.findAll();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getIdEleve()).isEqualTo(1L);
        assertThat(result.get(0).getNom()).isEqualTo("Dupont");
        assertThat(result.get(1).getIdEleve()).isEqualTo(2L);
        assertThat(result.get(1).getPrenom()).isEqualTo("Lina");
    }

    @Test
    void findById_shouldReturnDtoWhenPresent() {
        Eleve entity = buildEleve(1L, "Dupont", "Leo", "leo@ecole.fr");
        when(eleveRepository.findById(1L)).thenReturn(Optional.of(entity));

        Optional<EleveDto> result = eleveService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get().getIdEleve()).isEqualTo(1L);
        assertThat(result.get().getEmail()).isEqualTo("leo@ecole.fr");
    }

    @Test
    void save_shouldPersistAndReturnMappedDto() {
        EleveDto input = new EleveDto();
        input.setNom("Durand");
        input.setPrenom("Eva");
        input.setDateNaissance(LocalDate.of(2012, 1, 10));
        input.setAdresse("10 rue des Ecoles");
        input.setEmail("eva@ecole.fr");
        input.setTelephone("0600000000");

        when(eleveRepository.save(any(Eleve.class))).thenAnswer(invocation -> {
            Eleve saved = invocation.getArgument(0);
            saved.setIdEleve(10L);
            return saved;
        });

        EleveDto result = eleveService.save(input);

        assertThat(result.getIdEleve()).isEqualTo(10L);
        assertThat(result.getNom()).isEqualTo("Durand");
        assertThat(result.getPrenom()).isEqualTo("Eva");
        verify(eleveRepository).save(any(Eleve.class));
    }

    @Test
    void deleteById_shouldDelegateToRepository() {
        eleveService.deleteById(5L);

        verify(eleveRepository).deleteById(5L);
    }

    private Eleve buildEleve(Long id, String nom, String prenom, String email) {
        Eleve eleve = new Eleve();
        eleve.setIdEleve(id);
        eleve.setNom(nom);
        eleve.setPrenom(prenom);
        eleve.setDateNaissance(LocalDate.of(2013, 5, 14));
        eleve.setAdresse("12 rue des Ecoles");
        eleve.setEmail(email);
        eleve.setTelephone("0611223344");
        return eleve;
    }
}
