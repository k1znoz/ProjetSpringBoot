package fr.cda.ecole.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Enseignement;

@Repository
public interface EnseignementRepository extends JpaRepository<Enseignement, Long> {

    List<Enseignement> findByClasseIdClasse(Long idClasse);

    List<Enseignement> findByEnseignantIdEnseignant(Long idEnseignant);
}
