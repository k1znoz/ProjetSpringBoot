package fr.cda.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.cda.ecole.entity.Enseignant;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

    boolean existsByEmail(String email);
}
