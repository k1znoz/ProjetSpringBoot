package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByEleveIdEleve(Long idEleve);

    List<Note> findByMatiereIdMatiere(Long idMatiere);
}
