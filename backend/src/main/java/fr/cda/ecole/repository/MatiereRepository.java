package fr.cda.ecole.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long> {

    Optional<Matiere> findByNomMatiere(String nomMatiere);
}
