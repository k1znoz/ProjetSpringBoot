package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.cda.ecole.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByEleveIdEleve(Long idEleve);

    List<Note> findByMatiereIdMatiere(Long idMatiere);
}
