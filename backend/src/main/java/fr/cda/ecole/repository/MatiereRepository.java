package fr.cda.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Matiere;

@Repository
public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    Optional<Matiere> findByNomMatiere(String nomMatiere);
}
