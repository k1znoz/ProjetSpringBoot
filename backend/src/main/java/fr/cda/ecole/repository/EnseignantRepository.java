package fr.cda.ecole.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.cda.ecole.entity.Enseignant;

@Repository
public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {

    boolean existsByEmail(String email);
}
